package com.liws.config;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.liws.App;
import com.sun.javafx.fxml.BeanAdapter;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Configuration
public class ServerBootStrapConfig implements ApplicationContextAware{
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    @Resource
    private ChannelHandler channelHandler;
    @Resource
    private Map<ChannelOption<?>, ?> channelOptions;
    @Resource
    private InetSocketAddress socketAddress;
    @Resource
    private NioEventLoopGroup bossEventLoopGroup;
    @Resource
    private NioEventLoopGroup workerEventLoopGroup;
    @Resource
    private List<String> bindChildHandlers;
    @Resource
    private List<ChannelHandler> bindLogicHandlers;
    @Resource
    private DefaultEventExecutorGroup eventExecutorGroup;

    @Resource
    private ListeningExecutorService executorService;
    private ApplicationContext applicationContext;
    private final List<ListenableFuture<?>> futures = Lists.newArrayList();


    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Bean
    @Scope(SCOPE_PROTOTYPE)
    public ServerBootstrap initServerBootstrap(){
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossEventLoopGroup, workerEventLoopGroup);
        bootstrap.channel(NioServerSocketChannel.class);
        if (channelOptions != null) {
            final Set<ChannelOption<?>> keySet = channelOptions.keySet();
            for (final ChannelOption option : keySet) {
                bootstrap.option(option, channelOptions.get(option));
                bootstrap.childOption(option, channelOptions.get(option));
            }
        }
        if (channelHandler != null) {
            bootstrap.handler(channelHandler);
        }
        bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                final ChannelPipeline pipeline = ch.pipeline();
                for (final String handler : bindChildHandlers) {
                    System.out.println(handler);
                    pipeline.addLast(applicationContext.getBean(handler, ChannelHandler.class));
                }
                if (eventExecutorGroup == null) {
                    for (final ChannelHandler handler : bindLogicHandlers) {
                        pipeline.addLast(handler);
                    }
                } else {
                    for (final ChannelHandler handler : bindLogicHandlers) {
                        pipeline.addLast(eventExecutorGroup,handler);
                    }
                }
            }
        });

        return bootstrap;
    }

    @PostConstruct
    public void start() throws InterruptedException {
        final ServerBootstrap bootstrap = applicationContext.getBean("initServerBootstrap",
                ServerBootstrap.class);
        final Channel channel = bootstrap.bind(socketAddress).sync().channel();
        LOGGER.info("Start SocketServer Success, Listing port: "+ socketAddress.getPort());
        futures.add(executorService.submit(new Runnable() {

            @Override
            public void run() {
                try {
                    channel.closeFuture().sync();
                } catch (final InterruptedException e) {
                    throw new IllegalStateException(e);
                }
            }

        }));
    }


    /**
     * 获取{@link #futures}属性的值
     *
     * @return {@link #futures}属性的值
     */
    @Bean
    public List<ListenableFuture<?>> futures() {
        return futures;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}
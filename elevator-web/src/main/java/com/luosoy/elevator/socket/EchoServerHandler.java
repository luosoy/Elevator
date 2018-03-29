package com.luosoy.elevator.socket;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

@ChannelHandler.Sharable
public class EchoServerHandler extends SimpleChannelInboundHandler<byte[]> {

    private static final Logger logger = LoggerFactory.getLogger(EchoServerHandler.class);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.info(ctx.channel().localAddress().toString() + " 通道激活！");
        //连接成功的时候发送"ok"给客户端
        ctx.writeAndFlush(Unpooled.copiedBuffer("ok", CharsetUtil.UTF_8));
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        logger.info(ctx.channel().localAddress().toString() + " 通道关闭！");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, byte[] msg) throws Exception {
        //处理客户端发来的消息
        System.out.println(new String(msg,CharsetUtil.UTF_8));
    }

    /**
     * 功能：读取完毕客户端发送过来的数据之后的操作
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        logger.info("服务端接收数据完毕..");
        //刷空所有数据，并在执行完毕后，关闭通道
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }

    /**
     * 功能：服务端发生异常的操作
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        logger.error("异常信息:" + cause.getMessage(), cause);
    }

}

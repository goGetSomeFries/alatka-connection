package com.alatka.connection.core.module;

/**
 * 模块构建器<br><br>
 * 模块主要分为5类：<br>
 * alatka.connection.definition --> 基础支持模块<br>
 * alatka.connection.route.inbound --> 入站模块<br>
 * alatka.connection.route.outbound --> 出站模块<br>
 * alatka.connection.route.bypass --> 出站旁路模块<br>
 * alatka.connection.route.processors --> 处理器模块<br>
 * <p></p>
 *
 * @param <T> model实体类型
 * @author ybliu
 */
public interface ModuleBuilder<T> {

    /**
     * 构建模块
     *
     * @param model 模型实体
     */
    void build(T model);
}

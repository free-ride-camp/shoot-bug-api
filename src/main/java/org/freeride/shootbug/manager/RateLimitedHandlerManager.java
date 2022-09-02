package org.freeride.shootbug.manager;

import org.freeride.shootbug.handler.RateLimitedHandler;
import org.freeride.shootbug.handler.type.RateLimitedType;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @author ZhaoHe(hezhao @ dianhun.cn)
 * @date 2022/9/2 14:45
 */

@Component
public class RateLimitedHandlerManager implements ApplicationContextAware {
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public RateLimitedHandler getHandlerByType(RateLimitedType type) {
        Collection<RateLimitedHandler> handlers = applicationContext.getBeansOfType(RateLimitedHandler.class).values();
        return handlers.stream().filter(r -> r.supports(type)).findAny().orElseThrow();
    }
}

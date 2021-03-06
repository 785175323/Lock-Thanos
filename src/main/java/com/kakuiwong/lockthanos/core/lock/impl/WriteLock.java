package com.kakuiwong.lockthanos.core.lock.impl;

import com.kakuiwong.lockthanos.bean.LockParam;
import com.kakuiwong.lockthanos.core.lock.ThanosLockI;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RedissonClient;

/**
 * @author gaoyang
 * @email 785175323@qq.com
 */
public class WriteLock implements ThanosLockI {

    private RedissonClient redissonClient;

    public WriteLock(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    @Override
    public boolean lock(LockParam param) {
            RReadWriteLock lock = redissonClient.getReadWriteLock(param.getLockName());
            return defaultLock(lock.writeLock(), param);
    }

    @Override
    public boolean unLock(LockParam param) {
        RReadWriteLock lock = redissonClient.getReadWriteLock(param.getLockName());
        return defaultUnLock(lock.writeLock());
    }
}

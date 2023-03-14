package com.github.zhuyaotong.demo.businessdevelopment;

import org.h2.util.StringUtils;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

//public class Redis {
//
//    @Autowired
//    private StringRedisTemplate stringRedisTemplate;
//    private AtomicInteger atomicInteger = new AtomicInteger();
//
//    @PostConstruct
//    public void wrongInit() {
//        //初始化1000个城市数据到Redis，所有缓存数据有效期30秒
//        IntStream.rangeClosed(1, 1000).forEach(i -> stringRedisTemplate.opsForValue()
//                .set("city" + i, getCityFromDb(i), 30, TimeUnit.SECONDS));
//        log.info("Cache init finished");
//
//        //每秒一次，输出数据库访问的QPS
//
//        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
//            log.info("DB QPS : {}", atomicInteger.getAndSet(0));
//        }, 0, 1, TimeUnit.SECONDS);
//    }
//
//
//    @PostConstruct
//    public void rightInit1() {
//        //这次缓存的过期时间是30秒+10秒内的随机延迟
//        IntStream.rangeClosed(1, 1000).forEach(i -> stringRedisTemplate.opsForValue()
//                .set("city" + i, getCityFromDb(i), 30 + ThreadLocalRandom.current().nextInt(10), TimeUnit.SECONDS));
//        log.info("Cache init finished");
//        //同样1秒一次输出数据库QPS：
//        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
//            log.info("DB QPS : {}", atomicInteger.getAndSet(0));
//        }, 0, 1, TimeUnit.SECONDS);
//    }
//
//
//    @PostConstruct
//    public void rightInit2() throws InterruptedException {
//        CountDownLatch countDownLatch = new CountDownLatch(1);
//        //每隔30秒全量更新一次缓存
//        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
//            IntStream.rangeClosed(1, 1000).forEach(i -> {
//                String data = getCityFromDb(i);
//                //模拟更新缓存需要一定的时间
//                try {
//                    TimeUnit.MILLISECONDS.sleep(20);
//                } catch (InterruptedException e) { }
//                if (!StringUtils.isEmpty(data)) {
//                    //缓存永不过期，被动更新
//                    stringRedisTemplate.opsForValue().set("city" + i, data);
//                }
//            });
//            log.info("Cache update finished");
//            //启动程序的时候需要等待首次更新缓存完成
//            countDownLatch.countDown();
//        }, 0, 30, TimeUnit.SECONDS);
//
//        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
//            log.info("DB QPS : {}", atomicInteger.getAndSet(0));
//        }, 0, 1, TimeUnit.SECONDS);
//
//        countDownLatch.await();
//    }
//
//
//    @PostConstruct
//    public void init() {
//        //初始化一个热点数据到Redis中，过期时间设置为5秒
//        stringRedisTemplate.opsForValue().set("hotsopt", getExpensiveData(), 5, TimeUnit.SECONDS);
//        //每隔1秒输出一下回源的QPS
//
//        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
//            log.info("DB QPS : {}", atomicInteger.getAndSet(0));
//        }, 0, 1, TimeUnit.SECONDS);
//    }
//
//    @GetMapping("wrong")
//    public String wrong() {
//        String data = stringRedisTemplate.opsForValue().get("hotsopt");
//        if (StringUtils.isEmpty(data)) {
//            data = getExpensiveData();
//            //重新加入缓存，过期时间还是5秒
//            stringRedisTemplate.opsForValue().set("hotsopt", data, 5, TimeUnit.SECONDS);
//        }
//        return data;
//    }
//
//    @GetMapping("city")
//    public String city() {
//        //随机查询一个城市
//        int id = ThreadLocalRandom.current().nextInt(1000) + 1;
//        String key = "city" + id;
//        String data = stringRedisTemplate.opsForValue().get(key);
//        if (data == null) {
//            //回源到数据库查询
//            data = getCityFromDb(id);
//            if (!StringUtils.isEmpty(data))
//                //缓存30秒过期
//                stringRedisTemplate.opsForValue().set(key, data, 30, TimeUnit.SECONDS);
//        }
//        return data;
//    }
//
//    private String getCityFromDb(int cityId) {
//        //模拟查询数据库，查一次增加计数器加一
//        atomicInteger.incrementAndGet();
//        return "citydata" + System.currentTimeMillis();
//    }
//
//
//    @Autowired
//    private RedissonClient redissonClient;
//    @GetMapping("right")
//    public String right() {
//        String data = stringRedisTemplate.opsForValue().get("hotsopt");
//        if (StringUtils.isEmpty(data)) {
//            RLock locker = redissonClient.getLock("locker");
//            //获取分布式锁
//            if (locker.tryLock()) {
//                try {
//                    data = stringRedisTemplate.opsForValue().get("hotsopt");
//                    //双重检查，因为可能已经有一个B线程过了第一次判断，在等锁，然后A线程已经把数据写入了Redis中
//                    if (StringUtils.isEmpty(data)) {
//                        //回源到数据库查询
//                        data = getExpensiveData();
//                        stringRedisTemplate.opsForValue().set("hotsopt", data, 5, TimeUnit.SECONDS);
//                    }
//                } finally {
//                    //别忘记释放，另外注意写法，获取锁后整段代码try+finally，确保unlock万无一失
//                    locker.unlock();
//                }
//            }
//        }
//        return data;
//    }
//}

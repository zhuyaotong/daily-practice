package com.github.zhuyaotong.designpatterns.oop.composite;

public class FeignClient { // Feign Client框架代码
    //...省略其他代码...
    public void encode(String url) {
        //...
    }
}

class CustomizedFeignClient extends FeignClient {
    private String url;

    public void demofunction(FeignClient feignClient) {
        //...
        feignClient.encode(url);
        //...
    }

    @Override
    public void encode(String url) {
        //...重写encode的实现...
        System.out.println("重写encode的实现...");
    }

    public static void main(String[] args) {
        // 调用
        CustomizedFeignClient client = new CustomizedFeignClient();

        client.demofunction(client);
    }
}
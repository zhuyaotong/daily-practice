package com.github.zhuyaotong.demo.businessdevelopment;


import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//购物车
//@Data
//public class Cart {
//    //商品清单
//    private List<Item> items = new ArrayList<>();
//    //总优惠
//    private BigDecimal totalDiscount;
//    //商品总价
//    private BigDecimal totalItemPrice;
//    //总运费
//    private BigDecimal totalDeliveryPrice;
//    //应付总价
//    private BigDecimal payPrice;
//}
//
////购物车中的商品
//@Data
//class Item {
//    //商品ID
//    private long id;
//    //商品数量
//    private int quantity;
//    //商品单价
//    private BigDecimal price;
//    //商品优惠
//    private BigDecimal couponPrice;
//    //商品运费
//    private BigDecimal deliveryPrice;
//}
//
////普通用户购物车处理
//class NormalUserCart {
//
//    @GetMapping("wrong")
//    public Cart wrong(@RequestParam("userId") int userId) {
//        //根据用户ID获得用户类型
//        String userCategory = Db.getUserCategory(userId);
//        //普通用户处理逻辑
//        if (userCategory.equals("Normal")) {
//            NormalUserCart normalUserCart = new NormalUserCart();
//            return normalUserCart.process(userId, items);
//        }
//        //VIP用户处理逻辑
//        if (userCategory.equals("Vip")) {
//            VipUserCart vipUserCart = new VipUserCart();
//            return vipUserCart.process(userId, items);
//        }
//        //内部用户处理逻辑
//        if (userCategory.equals("Internal")) {
//            InternalUserCart internalUserCart = new InternalUserCart();
//            return internalUserCart.process(userId, items);
//        }
//
//        return null;
//    }
//
//    public Cart process(long userId, Map<Long, Integer> items) {
//        Cart cart = new Cart();
//
//        //把Map的购物车转换为Item列表
//        List<Item> itemList = new ArrayList<>();
//        items.entrySet().stream().forEach(entry -> {
//            Item item = new Item();
//            item.setId(entry.getKey());
//            item.setPrice(Db.getItemPrice(entry.getKey()));
//            item.setQuantity(entry.getValue());
//            itemList.add(item);
//        });
//        cart.setItems(itemList);
//
//        //处理运费和商品优惠
//        itemList.stream().forEach(item -> {
//            //运费为商品总价的10%
//            item.setDeliveryPrice(item.getPrice()
//                    .multiply(BigDecimal.valueOf(item.getQuantity()))
//                    .multiply(new BigDecimal("0.1")));
//            //无优惠
//            item.setCouponPrice(BigDecimal.ZERO);
//        });
//
//        //计算商品总价
//        cart.setTotalItemPrice(cart.getItems().stream()
//                .map(item -> item.getPrice()
//                        .multiply(BigDecimal.valueOf(item.getQuantity()))
//                )
//                .reduce(BigDecimal.ZERO, BigDecimal::add));
//        //计算运费总价
//        cart.setTotalDeliveryPrice(cart.getItems().stream()
//                .map(Item::getDeliveryPrice)
//                .reduce(BigDecimal.ZERO, BigDecimal::add));
//        //计算总优惠
//        cart.setTotalDiscount(cart.getItems().stream()
//                .map(Item::getCouponPrice)
//                .reduce(BigDecimal.ZERO, BigDecimal::add));
//        //应付总价=商品总价+运费总价-总优惠
//        cart.setPayPrice(cart.getTotalItemPrice().add(cart.getTotalDeliveryPrice()).subtract(cart.getTotalDiscount()));
//        return cart;
//    }
//}
//
//
//abstract class AbstractCart {
//    //处理购物车的大量重复逻辑在父类实现
//    public Cart process(long userId, Map<Long, Integer> items) {
//
//        Cart cart = new Cart();
//
//        List<Item> itemList = new ArrayList<>();
//        items.entrySet().stream().forEach(entry -> {
//            Item item = new Item();
//            item.setId(entry.getKey());
//            item.setPrice(Db.getItemPrice(entry.getKey()));
//            item.setQuantity(entry.getValue());
//            itemList.add(item);
//        });
//        cart.setItems(itemList);
//        //让子类处理每一个商品的优惠
//        itemList.stream().forEach(item -> {
//            processCouponPrice(userId, item);
//            processDeliveryPrice(userId, item);
//        });
//        //计算商品总价
//        cart.setTotalItemPrice(cart.getItems().stream().map(item -> item.getPrice()
//                .multiply(BigDecimal.valueOf(item.getQuantity()))).reduce(BigDecimal.ZERO, BigDecimal::add));
//        //计算总运费
//        cart.setTotalDeliveryPrice(cart.getItems().stream().map(Item::getDeliveryPrice).reduce(BigDecimal.ZERO, BigDecimal::add));
//        //计算总折扣
//        cart.setTotalDiscount(cart.getItems().stream().map(Item::getCouponPrice).reduce(BigDecimal.ZERO, BigDecimal::add));
//        //计算应付价格
//        cart.setPayPrice(cart.getTotalItemPrice().add(cart.getTotalDeliveryPrice()).subtract(cart.getTotalDiscount()));
//        return cart;
//    }
//
//    //处理商品优惠的逻辑留给子类实现
//    protected abstract void processCouponPrice(long userId, Item item);
//    //处理配送费的逻辑留给子类实现
//    protected abstract void processDeliveryPrice(long userId, Item item);
//}
//
//
//@Service(value = "NormalUserCart")
//class NormalUserCart extends AbstractCart {
//
//    @Override
//    protected void processCouponPrice(long userId, Item item) {
//        item.setCouponPrice(BigDecimal.ZERO);
//    }
//
//    @Override
//    protected void processDeliveryPrice(long userId, Item item) {
//        item.setDeliveryPrice(item.getPrice()
//                .multiply(BigDecimal.valueOf(item.getQuantity()))
//                .multiply(new BigDecimal("0.1")));
//    }
//}
//
//
//@Service(value = "VipUserCart")
//class VipUserCart extends NormalUserCart {
//
//    @Override
//    protected void processCouponPrice(long userId, Item item) {
//        if (item.getQuantity() > 2) {
//            item.setCouponPrice(item.getPrice()
//                    .multiply(BigDecimal.valueOf(100 - Db.getUserCouponPercent(userId)).divide(new BigDecimal("100")))
//                    .multiply(BigDecimal.valueOf(item.getQuantity() - 2)));
//        } else {
//            item.setCouponPrice(BigDecimal.ZERO);
//        }
//    }
//}
//
//
//@Service(value = "InternalUserCart")
//class InternalUserCart extends AbstractCart {
//    @Override
//    protected void processCouponPrice(long userId, Item item) {
//        item.setCouponPrice(BigDecimal.ZERO);
//    }
//
//    @Override
//    protected void processDeliveryPrice(long userId, Item item) {
//        item.setDeliveryPrice(BigDecimal.ZERO);
//    }
//}
//
//
//@GetMapping("right")
//public Cart right(@RequestParam("userId") int userId) {
//    String userCategory = Db.getUserCategory(userId);
//    AbstractCart cart = (AbstractCart) applicationContext.getBean(userCategory + "UserCart");
//    return cart.process(userId, items);
//}
//
//
//
//public class BankService {
//
//    //创建用户方法
//    public static String createUser(String name, String identity, String mobile, int age) throws IOException {
//        StringBuilder stringBuilder = new StringBuilder();
//        //字符串靠左，多余的地方填充_
//        stringBuilder.append(String.format("%-10s", name).replace(' ', '_'));
//        //字符串靠左，多余的地方填充_
//        stringBuilder.append(String.format("%-18s", identity).replace(' ', '_'));
//        //数字靠右，多余的地方用0填充
//        stringBuilder.append(String.format("%05d", age));
//        //字符串靠左，多余的地方用_填充
//        stringBuilder.append(String.format("%-11s", mobile).replace(' ', '_'));
//        //最后加上MD5作为签名
//        stringBuilder.append(DigestUtils.md2Hex(stringBuilder.toString()));
//        return Request.Post("http://localhost:45678/reflection/bank/createUser")
//                .bodyString(stringBuilder.toString(), ContentType.APPLICATION_JSON)
//                .execute().returnContent().asString();
//    }
//
//    //支付方法
//    public static String pay(long userId, BigDecimal amount) throws IOException {
//        StringBuilder stringBuilder = new StringBuilder();
//        //数字靠右，多余的地方用0填充
//        stringBuilder.append(String.format("%020d", userId));
//        //金额向下舍入2位到分，以分为单位，作为数字靠右，多余的地方用0填充
//        stringBuilder.append(String.format("%010d", amount.setScale(2, RoundingMode.DOWN).multiply(new BigDecimal("100")).longValue()));
//        //最后加上MD5作为签名
//        stringBuilder.append(DigestUtils.md2Hex(stringBuilder.toString()));
//        return Request.Post("http://localhost:45678/reflection/bank/pay")
//                .bodyString(stringBuilder.toString(), ContentType.APPLICATION_JSON)
//                .execute().returnContent().asString();
//    }
//}

//class Hello {
//
//    @GetMapping("server")
//    public APIResponse<OrderInfo> server(@RequestParam("userId") Long userId) {
//        APIResponse<OrderInfo> response = new APIResponse<>();
//        if (userId == null) {
//            //对于userId为空的情况，收单服务直接处理失败，给予相应的错误码和错误提示
//            response.setSuccess(false);
//            response.setCode(3001);
//            response.setMessage("Illegal userId");
//        } else if (userId == 1) {
//            //对于userId=1的用户，模拟订单服务对于风险用户的情况
//            response.setSuccess(false);
//            //把订单服务返回的错误码转换为收单服务错误码
//            response.setCode(3002);
//            response.setMessage("Internal Error, order is cancelled");
//            //同时日志记录内部错误
//            log.warn("用户 {} 调用订单服务失败，原因是 Risk order detected", userId);
//        } else {
//            //其他用户，下单成功
//            response.setSuccess(true);
//            response.setCode(2000);
//            response.setMessage("OK");
//            response.setData(new OrderInfo("Created", 2L));
//        }
//        return response;
//    }
//
//
//    @GetMapping("client")
//    public String client(@RequestParam(value = "error", defaultValue = "0") int error) {
//        String url = Arrays.asList("http://localhost:45678/apiresposne/server?userId=2",
//                "http://localhost:45678/apiresposne/server2",
//                "http://localhost:45678/apiresposne/server?userId=",
//                "http://localhost:45678/apiresposne/server?userId=1").get(error);
//
//        //第一层，先看状态码，如果状态码不是200，不处理响应体
//        String response = "";
//        try {
//            response = Request.Get(url).execute().returnContent().asString();
//        } catch (HttpResponseException e) {
//            log.warn("请求服务端出现返回非200", e);
//            return "服务器忙，请稍后再试！";
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        //状态码为200的情况下处理响应体
//        if (!response.equals("")) {
//            try {
//                APIResponse<OrderInfo> apiResponse = objectMapper.readValue(response, new TypeReference<APIResponse<OrderInfo>>() {
//                });
//                //第二层，success是false直接提示用户
//                if (!apiResponse.isSuccess()) {
//                    return String.format("创建订单失败，请稍后再试，错误代码： %s 错误原因：%s", apiResponse.getCode(), apiResponse.getMessage());
//                } else {
//                    //第三层，往下解析OrderInfo
//                    OrderInfo orderInfo = apiResponse.getData();
//                    if ("Created".equals(orderInfo.getStatus()))
//                        return String.format("创建订单成功，订单号是：%s，状态是：%s", orderInfo.getOrderId(), orderInfo.getStatus());
//                    else
//                        return String.format("创建订单失败，请联系客服处理");
//                }
//            } catch (JsonProcessingException e) {
//                e.printStackTrace();
//            }
//        }
//        return "";
//    }
//}
//
//
////此段代码只是Demo，生产级应用还需要扩展很多细节
//@RestControllerAdvice
//@Slf4j
//public class APIResponseAdvice implements ResponseBodyAdvice<Object> {
//
//    //自动处理APIException，包装为APIResponse
//    @ExceptionHandler(APIException.class)
//    public APIResponse handleApiException(HttpServletRequest request, APIException ex) {
//        log.error("process url {} failed", request.getRequestURL().toString(), ex);
//        APIResponse apiResponse = new APIResponse();
//        apiResponse.setSuccess(false);
//        apiResponse.setCode(ex.getErrorCode());
//        apiResponse.setMessage(ex.getErrorMessage());
//        return apiResponse;
//    }
//
//    //仅当方法或类没有标记@NoAPIResponse才自动包装
//    @Override
//    public boolean supports(MethodParameter returnType, Class converterType) {
//        return returnType.getParameterType() != APIResponse.class
//                && AnnotationUtils.findAnnotation(returnType.getMethod(), NoAPIResponse.class) == null
//                && AnnotationUtils.findAnnotation(returnType.getDeclaringClass(), NoAPIResponse.class) == null;
//    }
//
//    //自动包装外层APIResposne响应
//    @Override
//    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
//                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
//                                  ServerHttpRequest request, ServerHttpResponse response) {
//        APIResponse apiResponse = new APIResponse();
//        apiResponse.setSuccess(true);
//        apiResponse.setMessage("OK");
//        apiResponse.setCode(2000);
//        apiResponse.setData(body);
//        return apiResponse;
//    }
//}
//
//
//public class APIVersionHandlerMapping extends RequestMappingHandlerMapping {
//    @Override
//    protected boolean isHandler(Class<?> beanType) {
//        return AnnotatedElementUtils.hasAnnotation(beanType, Controller.class);
//    }
//
//
//    @Override
//    protected void registerHandlerMethod(Object handler, Method method, RequestMappingInfo mapping) {
//        Class<?> controllerClass = method.getDeclaringClass();
//        //类上的APIVersion注解
//        APIVersion apiVersion = AnnotationUtils.findAnnotation(controllerClass, APIVersion.class);
//        //方法上的APIVersion注解
//        APIVersion methodAnnotation = AnnotationUtils.findAnnotation(method, APIVersion.class);
//        //以方法上的注解优先
//        if (methodAnnotation != null) {
//            apiVersion = methodAnnotation;
//        }
//
//        String[] urlPatterns = apiVersion == null ? new String[0] : apiVersion.value();
//
//        PatternsRequestCondition apiPattern = new PatternsRequestCondition(urlPatterns);
//        PatternsRequestCondition oldPattern = mapping.getPatternsCondition();
//        PatternsRequestCondition updatedFinalPattern = apiPattern.combine(oldPattern);
//        //重新构建RequestMappingInfo
//        mapping = new RequestMappingInfo(mapping.getName(), updatedFinalPattern, mapping.getMethodsCondition(),
//                mapping.getParamsCondition(), mapping.getHeadersCondition(), mapping.getConsumesCondition(),
//                mapping.getProducesCondition(), mapping.getCustomCondition());
//        super.registerHandlerMethod(handler, method, mapping);
//    }
//}
//
//class Hi {
//
//    private ExecutorService threadPool = Executors.newFixedThreadPool(2);
//
////我没有贴出两个文件上传方法uploadFile和uploadThumbnailFile的实现，它们在内部只是随机进行休眠然后返回文件名，对于本例来说不是很重要
//
//    public UploadResponse upload(UploadRequest request) {
//        UploadResponse response = new UploadResponse();
//        //上传原始文件任务提交到线程池处理
//        Future<String> uploadFile = threadPool.submit(() -> uploadFile(request.getFile()));
//        //上传缩略图任务提交到线程池处理
//        Future<String> uploadThumbnailFile = threadPool.submit(() -> uploadThumbnailFile(request.getFile()));
//        //等待上传原始文件任务完成，最多等待1秒
//        try {
//            response.setDownloadUrl(uploadFile.get(1, TimeUnit.SECONDS));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        //等待上传缩略图任务完成，最多等待1秒
//        try {
//            response.setThumbnailDownloadUrl(uploadThumbnailFile.get(1, TimeUnit.SECONDS));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return response;
//    }
//
//
//    //计数器，作为上传任务的ID
//    private AtomicInteger atomicInteger = new AtomicInteger(0);
//    //暂存上传操作的结果，生产代码需要考虑数据持久化
//    private ConcurrentHashMap<String, SyncQueryUploadTaskResponse> downloadUrl = new ConcurrentHashMap<>();
//    //异步上传操作
//    public AsyncUploadResponse asyncUpload(AsyncUploadRequest request) {
//        AsyncUploadResponse response = new AsyncUploadResponse();
//        //生成唯一的上传任务ID
//        String taskId = "upload" + atomicInteger.incrementAndGet();
//        //异步上传操作只返回任务ID
//        response.setTaskId(taskId);
//        //提交上传原始文件操作到线程池异步处理
//        threadPool.execute(() -> {
//            String url = uploadFile(request.getFile());
//            //如果ConcurrentHashMap不包含Key，则初始化一个SyncQueryUploadTaskResponse，然后设置DownloadUrl
//            downloadUrl.computeIfAbsent(taskId, id -> new SyncQueryUploadTaskResponse(id)).setDownloadUrl(url);
//        });
//        //提交上传缩略图操作到线程池异步处理
//        threadPool.execute(() -> {
//            String url = uploadThumbnailFile(request.getFile());
//            downloadUrl.computeIfAbsent(taskId, id -> new SyncQueryUploadTaskResponse(id)).setThumbnailDownloadUrl(url);
//        });
//        return response;
//    }
//
//
//    //syncQueryUploadTask接口入参
//    @Data
//    @RequiredArgsConstructor
//    public class SyncQueryUploadTaskRequest {
//        private final String taskId;//使用上传文件任务ID查询上传结果
//    }
//    //syncQueryUploadTask接口出参
//    @Data
//    @RequiredArgsConstructor
//    public class SyncQueryUploadTaskResponse {
//        private final String taskId; //任务ID
//        private String downloadUrl; //原始文件下载URL
//        private String thumbnailDownloadUrl; //缩略图下载URL
//    }
//
//    public SyncQueryUploadTaskResponse syncQueryUploadTask(SyncQueryUploadTaskRequest request) {
//        SyncQueryUploadTaskResponse response = new SyncQueryUploadTaskResponse(request.getTaskId());
//        //从之前定义的downloadUrl ConcurrentHashMap查询结果
//        response.setDownloadUrl(downloadUrl.getOrDefault(request.getTaskId(), response).getDownloadUrl());
//        response.setThumbnailDownloadUrl(downloadUrl.getOrDefault(request.getTaskId(), response).getThumbnailDownloadUrl());
//        return response;
//    }
//}


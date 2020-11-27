##### 基于netty实现http服务器

	mrh-netty-http-server
	
	netty pipeline handler 顺序列表：
	
		1，io.netty.handler.codec.http.HttpServerCodec.HttpServerCodec
		
			netty 自带 handler，http 请求接收和响应处理
		
		2，io.netty.handler.codec.http.HttpObjectAggregator.HttpObjectAggregator
		
			netty 自带 handler，http 请求报文大小限制
		
		3，org.lushen.mrh.http.server.netty.handler.HttpFilterHandler.HttpFilterHandler
		
			自定义 handler，http 请求过滤规则定义
		
		4，org.lushen.mrh.http.server.netty.handler.HttpBodyCodec.HttpBodyCodec
		
			自定义 handler，http 请求响应 body 与 byte[] 转换
		
		5，org.lushen.mrh.http.server.netty.handler.HttpCryptoCodec.HttpCryptoCodec
		
			自定义 handler，http 请求响应 body 加解密
		
		6，org.lushen.mrh.http.server.netty.handler.HttpResolverHandler.HttpResolverHandler
		
			自定义 handler，http 请求响应业务处理和异常统一处理

##### 启动

	springboot方式启动：
	
		①，在application.properties中添加配置：
		
			org.lushen.mrh.http.server.port=8080
			org.lushen.mrh.http.server.acceptors=2
			org.lushen.mrh.http.server.workers=100
		
		②，启动springboot应用：
		
			@SpringBootApplication
			@ComponentScan(basePackageClasses=HttpServerRunner.class)
			public class HttpServerRunner {
			
				public static void main(String[] args) throws Exception {
					SpringApplication application = new SpringApplication(HttpServerRunner.class);
					application.setWebApplicationType(WebApplicationType.NONE);
					application.run(args);
					new CountDownLatch(1).await();
				}
			
			}
	
	纯Java方式启动：
		
		public static void main(String[] args) throws Exception {
		
			List<HttpFilter> filters = new ArrayList<HttpFilter>();
			filters.add(new HttpDebugFilter());
			filters.add(new HttpAllowMethodFilter(new HttpMethod[]{HttpMethod.POST}));
		
			HttpServerConfig serverConfig = new HttpServerConfig();
			serverConfig.setPort(8080);
		
			HttpServer httpServer = new HttpServer(serverConfig, filters, new HttpServerBusiness());
			httpServer.run();
			httpServer.close();
		
		}
	
	其他详细配置查看代码


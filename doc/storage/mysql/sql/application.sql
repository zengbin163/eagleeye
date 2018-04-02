CREATE TABLE `application` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '唯一主键',
  `name` varchar(100) NOT NULL DEFAULT '' COMMENT '应用名称',
  `type` varchar(50) NOT NULL DEFAULT '' COMMENT '类型 1:生产者 0:消费者',
  PRIMARY KEY (`id`),
  UNIQUE KEY `应用名词索引` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
CREATE TABLE `department` (
  `id`   bigint      AUTO_INCREMENT PRIMARY KEY,
  `name` varchar(32) NOT NULL COMMENT '名字',
  `tel`  varchar(18) DEFAULT NULL COMMENT '电话'
) COMMENT '部门表';

INSERT INTO department(id, name, tel) VALUES
     (1, '全部部门', '-'),
     (2, '开发部', '123'),
     (3, '测试产品部', '789'),
     (4, '运维部', '456');
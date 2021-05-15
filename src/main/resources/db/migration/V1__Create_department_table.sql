CREATE TABLE `tbl_department` (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(32) NOT NULL,
  tel VARCHAR(18) DEFAULT NULL
);

INSERT INTO tbl_department(name, tel) VALUES
     ('全部部门', '-'),
     ('开发部', '123'),
     ('测试产品部', '789'),
     ('运维部', '456')
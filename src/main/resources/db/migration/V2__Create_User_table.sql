CREATE TABLE `user` (
  `id`            bigint      AUTO_INCREMENT PRIMARY KEY,
  `version`       int         NOT NULL DEFAULT 0 COMMENT '乐观锁标识',
  `name`          varchar(32) NOT NULL COMMENT '姓名',
  `age`           int         DEFAULT NULL COMMENT '年龄',
  `birthday`      datetime    DEFAULT NULL COMMENT '生日',
  `department_id` varchar(32) NOT NULL COMMENT '部门 id',
  `rank`          int         NOT NULL DEFAULT 0 COMMENT '排序值',
  `is_deleted`    boolean     NOT NULL DEFAULT false COMMENT '逻辑删除标识'
) COMMENT '用户表';

INSERT INTO `user` (id, name, age, birthday, department_id) VALUES
    (1, '阿熊', 18, '2003-08-08 10:00:00', '2'),
    (2, '老狗', 30, '1991-02-20 15:27:20', '4');
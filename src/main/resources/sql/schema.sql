
drop table job_config if exists;
CREATE TABLE `job_config` (
  `id` tinyint(10) NOT NULL COMMENT '主键',
  `job_name` varchar(100) CHARACTER SET utf8 NOT NULL COMMENT '任务名称',
  `job_group` varchar(10) CHARACTER SET utf8 NOT NULL COMMENT '任务组',
  `job_desc` varchar(100) CHARACTER SET utf8 DEFAULT NULL COMMENT '描述',
  `job_cron` varchar(100) CHARACTER SET utf8 NOT NULL COMMENT '定时表达式',
  `job_type` varchar(50) NOT NULL COMMENT '任务类别',
  `job_status` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '任务状态',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

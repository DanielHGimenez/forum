CREATE DATABASE `forum`;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `forum`.`usuario`;
CREATE TABLE `forum`.`usuario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  `senha` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_nome` (`nome`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Table structure for table `pergunta`
--

DROP TABLE IF EXISTS `forum`.`pergunta`;
CREATE TABLE `forum`.`pergunta` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `data_criacao` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `texto` varchar(255) DEFAULT NULL,
  `fk_usuario_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_usuario` (`fk_usuario_id`),
  CONSTRAINT `FK_pergunta_usuario` FOREIGN KEY (`fk_usuario_id`) REFERENCES `forum`.`usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

--
-- Table structure for table `resposta`
--

DROP TABLE IF EXISTS `forum`.`resposta`;
CREATE TABLE `forum`.`resposta` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `data_criacao` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `texto` varchar(255) DEFAULT NULL,
  `fk_pergunta_id` bigint(20) DEFAULT NULL,
  `fk_usuario_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_resposta_pergunta` (`fk_pergunta_id`),
  KEY `FK_resposta_usuario` (`fk_usuario_id`),
  CONSTRAINT `FK_resposta_usuario` FOREIGN KEY (`fk_usuario_id`) REFERENCES `forum`.`usuario` (`id`),
  CONSTRAINT `FK_resposta_pergunta` FOREIGN KEY (`fk_pergunta_id`) REFERENCES `forum`.`pergunta` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
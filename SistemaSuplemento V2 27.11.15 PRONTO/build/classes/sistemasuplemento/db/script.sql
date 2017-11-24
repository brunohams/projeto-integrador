SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `suplementos` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
USE `suplementos` ;

-- -----------------------------------------------------
-- Table `suplementos`.`tbtiposuplemento`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `suplementos`.`tbtiposuplemento` (
  `idtiposuplemento` INT NOT NULL AUTO_INCREMENT ,
  `nome` VARCHAR(60) NULL ,
  PRIMARY KEY (`idtiposuplemento`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `suplementos`.`tbcondicaopagamento`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `suplementos`.`tbcondicaopagamento` (
  `idcondicaopagamento` INT NOT NULL AUTO_INCREMENT ,
  `codigo` VARCHAR(10) NULL ,
  `nome` VARCHAR(60) NULL ,
  PRIMARY KEY (`idcondicaopagamento`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `suplementos`.`tbestado`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `suplementos`.`tbestado` (
  `idestado` INT NOT NULL AUTO_INCREMENT ,
  `codigo` VARCHAR(12) NULL ,
  `nome` VARCHAR(45) NULL ,
  PRIMARY KEY (`idestado`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `suplementos`.`tbcidade`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `suplementos`.`tbcidade` (
  `idcidade` INT NOT NULL AUTO_INCREMENT ,
  `codigo` VARCHAR(12) NULL ,
  `nome` VARCHAR(45) NULL ,
  `idestado` INT NOT NULL ,
  PRIMARY KEY (`idcidade`) ,
  INDEX `fk_Cidade_Estado1` (`idestado` ASC) ,
  CONSTRAINT `fk_Cidade_Estado1`
    FOREIGN KEY (`idestado` )
    REFERENCES `suplementos`.`tbestado` (`idestado` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `suplementos`.`tbcliente`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `suplementos`.`tbcliente` (
  `idcliente` INT NOT NULL AUTO_INCREMENT ,
  `codigo` VARCHAR(45) NULL ,
  `nome` VARCHAR(60) NULL ,
  `telefone` VARCHAR(20) NULL ,
  `endereco` VARCHAR(150) NULL ,
  `datanascimento` VARCHAR(60) NULL ,
  `email` VARCHAR(120) NULL ,
  `idcidade` INT NOT NULL ,
  PRIMARY KEY (`idcliente`) ,
  INDEX `fk_Cliente_Cidade1` (`idcidade` ASC) ,
  CONSTRAINT `fk_Cliente_Cidade1`
    FOREIGN KEY (`idcidade` )
    REFERENCES `suplementos`.`tbcidade` (`idcidade` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `suplementos`.`tbformapagamento`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `suplementos`.`tbformapagamento` (
  `idformapagamento` INT NOT NULL AUTO_INCREMENT ,
  `codigo` VARCHAR(10) NULL ,
  `nome` VARCHAR(60) NULL ,
  PRIMARY KEY (`idformapagamento`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `suplementos`.`tbmarcasuplemento`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `suplementos`.`tbmarcasuplemento` (
  `idmarcasuplemento` INT NOT NULL AUTO_INCREMENT ,
  `nome` VARCHAR(45) NULL ,
  PRIMARY KEY (`idmarcasuplemento`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `suplementos`.`tbsuplemento`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `suplementos`.`tbsuplemento` (
  `idproduto` INT NOT NULL AUTO_INCREMENT ,
  `codigo` VARCHAR(45) NULL ,
  `nome` VARCHAR(45) NULL ,
  `preco` DECIMAL(12,2) NULL ,
  `quantidade` BIGINT NULL ,
  `idmarcaSuplemento` INT NOT NULL ,
  `idtiposuplemento` INT NOT NULL ,
  PRIMARY KEY (`idproduto`) ,
  INDEX `fk_Suplemento_MarcaSuplemento1` (`idmarcaSuplemento` ASC) ,
  INDEX `fk_Suplemento_Tipo Suplementos1` (`idtiposuplemento` ASC) ,
  CONSTRAINT `fk_Suplemento_MarcaSuplemento1`
    FOREIGN KEY (`idmarcaSuplemento` )
    REFERENCES `suplementos`.`tbmarcasuplemento` (`idmarcasuplemento` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Suplemento_Tipo Suplementos1`
    FOREIGN KEY (`idtiposuplemento` )
    REFERENCES `suplementos`.`tbtiposuplemento` (`idtiposuplemento` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `suplementos`.`tbpedido`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `suplementos`.`tbpedido` (
  `idpedido` INT NOT NULL AUTO_INCREMENT ,
  `total` DECIMAL(12,2) NULL ,
  `numero` INT NULL ,
  `dataemissao` DATETIME NULL ,
  `aprovacao` DATETIME NULL ,
  `valortotal` DECIMAL(23,3) NULL ,
  `idcondicaopagamento` INT NOT NULL ,
  `Idcliente` INT NOT NULL ,
  `idformapagamento` INT NOT NULL ,
  PRIMARY KEY (`idpedido`) ,
  INDEX `fk_Pedido_CondiçãoPagamento` (`idcondicaopagamento` ASC) ,
  INDEX `fk_Pedido_Cliente1` (`Idcliente` ASC) ,
  INDEX `fk_Pedido_FormaPagamento1` (`idformapagamento` ASC) ,
  CONSTRAINT `fk_Pedido_CondiçãoPagamento`
    FOREIGN KEY (`idcondicaopagamento` )
    REFERENCES `suplementos`.`tbcondicaopagamento` (`idcondicaopagamento` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Pedido_Cliente1`
    FOREIGN KEY (`Idcliente` )
    REFERENCES `suplementos`.`tbcliente` (`idcliente` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Pedido_FormaPagamento1`
    FOREIGN KEY (`idformapagamento` )
    REFERENCES `suplementos`.`tbformapagamento` (`idformapagamento` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `suplementos`.`tbpedidoitem`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `suplementos`.`tbpedidoitem` (
  `idpedidoitem` INT NOT NULL AUTO_INCREMENT ,
  `idproduto` INT NOT NULL ,
  `idpedido` INT NOT NULL ,
  `ordem` INT NULL ,
  `quantidade` INT NULL ,
  `valorunitario` DECIMAL(10,2) NULL ,
  `valortotal` DECIMAL(10,2) NULL ,
  PRIMARY KEY (`idpedidoitem`) ,
  INDEX `fk_tbpedidoitem_tbsuplemento1` (`idproduto` ASC) ,
  INDEX `fk_tbpedidoitem_tbpedido1` (`idpedido` ASC) ,
  CONSTRAINT `fk_tbpedidoitem_tbsuplemento1`
    FOREIGN KEY (`idproduto` )
    REFERENCES `suplementos`.`tbsuplemento` (`idproduto` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tbpedidoitem_tbpedido1`
    FOREIGN KEY (`idpedido` )
    REFERENCES `suplementos`.`tbpedido` (`idpedido` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

INSERT INTO `suplementos`.`tbestado` (`idestado`, `codigo`, `nome`) VALUES (1, '231', 'Santa Catarina');
INSERT INTO `suplementos`.`tbestado` (`idestado`, `codigo`, `nome`) VALUES (2, '232', 'São Paulo');
INSERT INTO `suplementos`.`tbestado` (`idestado`, `codigo`, `nome`) VALUES (3, '233', 'Rio de Janeiro');
INSERT INTO `suplementos`.`tbestado` (`idestado`, `codigo`, `nome`) VALUES (4, '234', 'Pernambuco');
INSERT INTO `suplementos`.`tbcidade` (`idcidade`, `codigo`, `nome`, `idestado`) VALUES (1, '111', 'Blumenau', 1);
INSERT INTO `suplementos`.`tbcidade` (`idcidade`, `codigo`, `nome`, `idestado`) VALUES (2, '112', 'Itapema', 1);
INSERT INTO `suplementos`.`tbcidade` (`idcidade`, `codigo`, `nome`, `idestado`) VALUES (3, '113', 'Indaial', 1);
INSERT INTO `suplementos`.`tbcidade` (`idcidade`, `codigo`, `nome`, `idestado`) VALUES (4, '114', 'Criciúma', 1);
INSERT INTO `suplementos`.`tbcidade` (`idcidade`, `codigo`, `nome`, `idestado`) VALUES (5, '121', 'São Paulo', 2);
INSERT INTO `suplementos`.`tbcidade` (`idcidade`, `codigo`, `nome`, `idestado`) VALUES (6, '122', 'São Bernado do Campo', 2);
INSERT INTO `suplementos`.`tbcidade` (`idcidade`, `codigo`, `nome`, `idestado`) VALUES (7, '123', 'Cafêlandia', 2);
INSERT INTO `suplementos`.`tbcidade` (`idcidade`, `codigo`, `nome`, `idestado`) VALUES (8, '131', 'Rio de Janeiro', 3);
INSERT INTO `suplementos`.`tbcidade` (`idcidade`, `codigo`, `nome`, `idestado`) VALUES (9, '132', 'Itaperuna', 3);
INSERT INTO `suplementos`.`tbcidade` (`idcidade`, `codigo`, `nome`, `idestado`) VALUES (10, '133', 'Natividade', 3);
INSERT INTO `suplementos`.`tbcidade` (`idcidade`, `codigo`, `nome`, `idestado`) VALUES (11, '141', 'Bom Conselho', 4);
INSERT INTO `suplementos`.`tbcidade` (`idcidade`, `codigo`, `nome`, `idestado`) VALUES (12, '142', 'Lagoa do Ouro', 4);
INSERT INTO `suplementos`.`tbcidade` (`idcidade`, `codigo`, `nome`, `idestado`) VALUES (13, '143', 'Correntes', 4);
INSERT INTO `suplementos`.`tbcidade` (`idcidade`, `codigo`, `nome`, `idestado`) VALUES (14, '144', 'São João', 4);
INSERT INTO `suplementos`.`tbcliente` (`idcliente`, `codigo`, `nome`, `telefone`, `endereco`, `datanascimento`, `email`, `idcidade`) VALUES (1, '1111', 'Jonathan Vieira dos Santos', '3232-1260', 'Rua Vitor Konder/ Nick Floor', '25 de Dezembro de 1988', 'josh.joe@gmail.com', 1);
INSERT INTO `suplementos`.`tbcliente` (`idcliente`, `codigo`, `nome`, `telefone`, `endereco`, `datanascimento`, `email`, `idcidade`) VALUES (2, '1112', 'Nicole Sousa Ferreira', '(91) 8177-8588', 'Rua Santo Antônio, 1122', '27 de Dezembro de 1990', 'NicoleSousaFerreira@dayrep.com', 4);
INSERT INTO `suplementos`.`tbcliente` (`idcliente`, `codigo`, `nome`, `telefone`, `endereco`, `datanascimento`, `email`, `idcidade`) VALUES (3, '1113', 'Erick Melo Fernandes', '(81) 6295-9800', 'Rua Macarani, 680', '18 de Março de 1998', 'ErickMeloFernandes@dayrep.com', 6);
INSERT INTO `suplementos`.`tbcliente` (`idcliente`, `codigo`, `nome`, `telefone`, `endereco`, `datanascimento`, `email`, `idcidade`) VALUES (4, '1114', 'Kai Oliveira Castro', '(81) 5130-7971', 'Rua Piracuruca, 1748', '11 de Dezembro de 1988', 'KaiOliveiraCastro@dayrep.com', 7);
INSERT INTO `suplementos`.`tbcliente` (`idcliente`, `codigo`, `nome`, `telefone`, `endereco`, `datanascimento`, `email`, `idcidade`) VALUES (5, '1115', 'Eduardo Fernandes Carvalho', '(12) 2642-2627', 'Praça Alexandrina Moreira Castilho, 948', '5 de Janeiro de 1995', 'EduardoFernandesCarvalho@rhyta.com', 10);
INSERT INTO `suplementos`.`tbcliente` (`idcliente`, `codigo`, `nome`, `telefone`, `endereco`, `datanascimento`, `email`, `idcidade`) VALUES (6, '1116', 'Carolina Pereira Barbosa', '(61) 7683-7644', 'Quadra CLS 210, 437', '3 de Junho de 1992', 'CarolinaPereiraBarbosa@armyspy.com', 4);
INSERT INTO `suplementos`.`tbcliente` (`idcliente`, `codigo`, `nome`, `telefone`, `endereco`, `datanascimento`, `email`, `idcidade`) VALUES (7, '1117', 'Luan Dias Castro', '(51) 9775-9312', 'Rua Pedro Weingartner, 1821', '12 de Abril de 1989', 'LuanDiasCastro@rhyta.com', 5);
INSERT INTO `suplementos`.`tbcliente` (`idcliente`, `codigo`, `nome`, `telefone`, `endereco`, `datanascimento`, `email`, `idcidade`) VALUES (8, '1118', 'Fernanda Costa Pinto', '(21) 5082-6486', 'Rua Leopoldo Miguez, 754', '17 de Abril de 1987', 'FernandaCostaPinto@jourrapide.com', 9);
INSERT INTO `suplementos`.`tbcliente` (`idcliente`, `codigo`, `nome`, `telefone`, `endereco`, `datanascimento`, `email`, `idcidade`) VALUES (9, '1119', 'Vitória Martins Souza', '(81) 9180-4968', 'Rua Tenente Antônio Florêncio do Nascimento, 1709', '25 de outubro de 1988', 'VitoriaMartinsSouza@armyspy.com', 11);
INSERT INTO `suplementos`.`tbcliente` (`idcliente`, `codigo`, `nome`, `telefone`, `endereco`, `datanascimento`, `email`, `idcidade`) VALUES (10, '1120', 'Paulo Cardoso Lima', '(61) 5396-2225', 'Quadra QRSW 08 Bloco A-09, 1279', '18 de fevereiro de 1978', 'PauloCardosoLima@armyspy.com', 11);
INSERT INTO `suplementos`.`tbcliente` (`idcliente`, `codigo`, `nome`, `telefone`, `endereco`, `datanascimento`, `email`, `idcidade`) VALUES (11, '1121', 'Murilo Silva Rocha', '(33) 4242-8540', 'Rua Pedra Azul, 220', '13 de Janeiro de 1999', 'MuriloSilvaRocha@rhyta.com', 14);


INSERT INTO `suplementos`.`tbtiposuplemento` (`idtiposuplemento`, `nome`) VALUES (1, 'BCAAs');
INSERT INTO `suplementos`.`tbtiposuplemento` (`idtiposuplemento`, `nome`) VALUES (2, 'CARBOIDRATOS');
INSERT INTO `suplementos`.`tbtiposuplemento` (`idtiposuplemento`, `nome`) VALUES (3, 'WHEY ');
INSERT INTO `suplementos`.`tbtiposuplemento` (`idtiposuplemento`, `nome`) VALUES (4, 'GLUTAMINA');
INSERT INTO `suplementos`.`tbtiposuplemento` (`idtiposuplemento`, `nome`) VALUES (5, 'CREATINA');
  
INSERT INTO `suplementos`.`tbmarcasuplemento` (`idmarcasuplemento`, `nome`) VALUES (1, 'PROBIÓTICA');
INSERT INTO `suplementos`.`tbmarcasuplemento` (`idmarcasuplemento`, `nome`) VALUES (2, 'Max Titanium');
INSERT INTO `suplementos`.`tbmarcasuplemento` (`idmarcasuplemento`, `nome`) VALUES (3, 'OPTIMUM NUTRITION');
INSERT INTO `suplementos`.`tbmarcasuplemento` (`idmarcasuplemento`, `nome`) VALUES (4, 'MHP');
INSERT INTO `suplementos`.`tbmarcasuplemento` (`idmarcasuplemento`, `nome`) VALUES (5, 'INTEGRALMÉDICA');

INSERT INTO `suplementos`.`tbsuplemento` (`idproduto`, `codigo`, `nome`, `preco`, `quantidade`, `idmarcaSuplemento`, `idtiposuplemento`) VALUES (1,  '38055', 'BCAA HUNK',  62.90, 10, 1, 1);
INSERT INTO `suplementos`.`tbsuplemento` (`idproduto`, `codigo`, `nome`, `preco`, `quantidade`, `idmarcaSuplemento`, `idtiposuplemento`) VALUES (2,  '38056', 'WAXY MAIZE', 60.90, 15, 2, 2);
INSERT INTO `suplementos`.`tbsuplemento` (`idproduto`, `codigo`, `nome`, `preco`, `quantidade`, `idmarcaSuplemento`, `idtiposuplemento`) VALUES (3,  '38057', 'ELLA WHEY', 90.95 , 30, 3, 3);
INSERT INTO `suplementos`.`tbsuplemento` (`idproduto`, `codigo`, `nome`, `preco`, `quantidade`, `idmarcaSuplemento`, `idtiposuplemento`) VALUES (4,  '38058', 'GLUTAMINE FUEL',  193.50, 60, 4, 4);
INSERT INTO `suplementos`.`tbsuplemento` (`idproduto`, `codigo`, `nome`, `preco`, `quantidade`, `idmarcaSuplemento`, `idtiposuplemento`) VALUES (5,  '38059', 'CREATINA POWDER CREAPURE',  59.15, 120, 5, 5);
INSERT INTO `suplementos`.`tbsuplemento` (`idproduto`, `codigo`, `nome`, `preco`, `quantidade`, `idmarcaSuplemento`, `idtiposuplemento`) VALUES (6,  '38060', 'BCAA',       99.90, 70, 1, 1);
INSERT INTO `suplementos`.`tbsuplemento` (`idproduto`, `codigo`, `nome`, `preco`, `quantidade`, `idmarcaSuplemento`, `idtiposuplemento`) VALUES (7,  '38061', 'NEW UP',     103.90, 35, 2, 2);
INSERT INTO `suplementos`.`tbsuplemento` (`idproduto`, `codigo`, `nome`, `preco`, `quantidade`, `idmarcaSuplemento`, `idtiposuplemento`) VALUES (8,  '38062', 'HIPER WHEY', 199.20 , 17, 3, 3);
INSERT INTO `suplementos`.`tbsuplemento` (`idproduto`, `codigo`, `nome`, `preco`, `quantidade`, `idmarcaSuplemento`, `idtiposuplemento`) VALUES (9,  '38063', 'CARNIBOL DARKNESS ',  124.72, 8, 4, 4);
INSERT INTO `suplementos`.`tbsuplemento` (`idproduto`, `codigo`, `nome`, `preco`, `quantidade`, `idmarcaSuplemento`, `idtiposuplemento`) VALUES (10, '38064', 'CREATINA ETHYL ESTER',  40.00, 4, 5, 5);
ALTER TABLE `suplementos`.`tbpedido` DROP COLUMN `total` ;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
/*
SQLyog Ultimate v11.25 (64 bit)
MySQL - 5.7.19 : Database - librarydb
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`librarydb` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `librarydb`;

/*Table structure for table `books` */

DROP TABLE IF EXISTS `books`;

CREATE TABLE `books` (
  `BookNumber` int(80) NOT NULL AUTO_INCREMENT,
  `BookName` varchar(100) DEFAULT NULL,
  `BookPress` varchar(100) DEFAULT NULL,
  `BookPrice` varchar(80) DEFAULT NULL,
  `BookType1` varchar(100) DEFAULT NULL,
  `BookType2` varchar(100) DEFAULT NULL,
  `BookType3` varchar(100) DEFAULT NULL,
  `BookQuantity` int(100) NOT NULL,
  PRIMARY KEY (`BookNumber`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

/*Data for the table `books` */

insert  into `books`(`BookNumber`,`BookName`,`BookPress`,`BookPrice`,`BookType1`,`BookType2`,`BookType3`,`BookQuantity`) values (1,'C语言程序设计基础','高等教育出版社','40.50','计算机','C语言','教材',9),(2,'Java面向对象程序设计','清华大学出版社','29.50','计算机','java语言','教材',4),(3,'高等数学（上册）设计','高等教育出版社','47.60','数学人','教材人','必修',0),(4,'高等数学（下册）设计','高等教育出版社','33.50','数学12','教材','必修',1),(5,'社会心理学第十一版设计','人民邮电出版社','128.00','心理学','学术','自然科学',19),(6,'毛泽东思想和中国特色社会主义理论体系概论（2021年版）设计','高等教育出版社','25.00','毛泽东和社会主义','教材','思想教育',99),(7,'数据结构与算法分析Java语言描述设计','机械工业出版社','69.00','计算机1','算法','java语言',12),(10,'设计大洼','的范围','59.33','大洼','大洼','大洼',44),(12,'无知之书1','啥1','88.5','这啥1','不知道','啥玩意',10),(18,'2PYTHON1','12012','1202','12012d','110','101',2011),(19,'110','110','110',NULL,NULL,NULL,110);

/*Table structure for table `borrow_books` */

DROP TABLE IF EXISTS `borrow_books`;

CREATE TABLE `borrow_books` (
  `bookNumber` int(80) NOT NULL AUTO_INCREMENT,
  `userNumber` varchar(80) NOT NULL,
  `bookName` varchar(100) DEFAULT NULL,
  `days` int(20) DEFAULT NULL,
  PRIMARY KEY (`bookNumber`)
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8;

/*Data for the table `borrow_books` */

insert  into `borrow_books`(`bookNumber`,`userNumber`,`bookName`,`days`) values (3,'20201234567','C语言程序设计基础',20211201),(9,'1','高等数学（下册）',20211205),(10,'1','高等数学（上册）',20211205),(11,'1','毛泽东思想和中国特色社会主义理论体系概论（2021年版）',20211205),(16,'12345678901','毛泽东思想和中国特色社会主义理论体系概论（2021年版）',20211207),(17,'12345678901','社会心理学第十一版',20211207),(37,'6748','社会心理学第十一版',20211209),(38,'6748','社会心理学第十一版',20211209),(39,'6748','社会心理学第十一版',20211209),(40,'6748','社会心理学第十一版',20211209),(41,'6748','社会心理学第十一版',20211209),(55,'12312312312','设计',20211214),(56,'12312312312','设计',20211214),(73,'20201111999','数据结构与算法分析JAVA语言描述设计',20211220),(75,'20201111999','设计大洼',20211220),(78,'20201111999','数据结构与算法分析JAVA语言描述设计',20211220),(79,'20201111927','2PYTHON1',20230606);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `Number` varchar(30) NOT NULL,
  `Password` varchar(20) DEFAULT NULL,
  `Question1` varchar(80) DEFAULT NULL,
  `Question2` varchar(80) DEFAULT NULL,
  `Question3` varchar(80) DEFAULT NULL,
  `Overdue` int(10) DEFAULT '0',
  PRIMARY KEY (`Number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`Number`,`Password`,`Question1`,`Question2`,`Question3`,`Overdue`) values ('0001','mzxwml1314','88','88','88',0),('12312312312','qwe123123','1','1','1',0),('12345678900','mzxwml1314','77','77','77',0),('12345678901','mzxwml123','45','45','45',0),('20201111905','ljk1234','110','88','sb',0),('20201111922','qwe123123','110','110','110',0),('20201111927','mzxwml1314','88','88','88',0),('20201111928','sgp3h201','W','w','w',0),('20201111992','mzxwml1314','110','110','110',0),('20201111999','mzxwml1314','45','45','45',0),('6748','lw123456','SSPU','li','lii',5);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

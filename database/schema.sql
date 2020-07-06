-- MySQL dump 10.13  Distrib 5.5.46, for Win32 (x86)
--
-- Host: localhost    Database: rhythm
-- ------------------------------------------------------
-- Server version	5.5.46

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `music`
--

DROP TABLE IF EXISTS `music`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `music` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `artist` varchar(40) NOT NULL,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `music`
--

LOCK TABLES `music` WRITE;
/*!40000 ALTER TABLE `music` DISABLE KEYS */;
INSERT INTO `music` VALUES (1,'임재범','비상'),(2,'이문세','소녀'),(3,'전인권','걱정말아요 그대');
/*!40000 ALTER TABLE `music` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rhythm_node1`
--

DROP TABLE IF EXISTS `rhythm_node1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rhythm_node1` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `music_id` int(11) NOT NULL,
  `rhythm_time` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rhythm_node1`
--

LOCK TABLES `rhythm_node1` WRITE;
/*!40000 ALTER TABLE `rhythm_node1` DISABLE KEYS */;
INSERT INTO `rhythm_node1` VALUES (2,1,'[1959, 2349, 2757, 3197, 4837, 5052, 5269, 6029, 6886, 7684, 8613, 9485, 10269, 10461, 10677, 10901, 11133, 11933, 12798, 13621, 14429, 15229, 15821, 16373, 16933, 17765, 18597, 19461, 20284, 21101, 21445, 21621, 21916, 24989, 25181, 25373, 25749, 25924, 26325, 26510, 28181, 28389, 28621, 29026, 29220, 29637, 29821]'),(3,1,'[1948, 2715, 3620, 4835, 6243, 6955, 7643, 8315, 9411, 10155]'),(4,1,'[1953, 4846, 6382, 6894, 7711, 8655, 11894, 13142, 14326, 16198, 17710, 18558, 19334, 20382, 21326, 22142, 22942, 24870, 26134, 28206, 29662, 31070, 32414, 33230, 34374]'),(5,3,'[5075, 8314, 10694, 12591, 14387]'),(6,1,'[1097, 1440, 1778, 2350, 2579, 2808, 3270]'),(7,3,'[1097, 1440, 1778, 2350, 2579, 2808, 3270, 1016, 1455, 2132, 2538, 3253, 4313, 4661]'),(8,3,'[1097, 1440, 1778, 2350, 2579, 2808, 3270, 1016, 1455, 2132, 2538, 3253, 4313, 4661]');
/*!40000 ALTER TABLE `rhythm_node1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rhythm_node2`
--

DROP TABLE IF EXISTS `rhythm_node2`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rhythm_node2` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `music_id` int(11) NOT NULL,
  `rhythm_time` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rhythm_node2`
--

LOCK TABLES `rhythm_node2` WRITE;
/*!40000 ALTER TABLE `rhythm_node2` DISABLE KEYS */;
INSERT INTO `rhythm_node2` VALUES (1,1,'[2260, 3035, 3451, 5204, 5819, 6564, 7299, 7988, 9059, 9770]'),(2,1,'[2742, 5254, 7302, 9366, 12303, 14702, 16438, 17918, 18742, 19750, 20614, 21502, 22318, 23150, 25134, 26318, 28614, 29854, 31686, 32606, 33798]'),(3,3,'[5952, 6956, 7424, 9312, 9745, 11602, 13521, 15057, 15484, 15965]'),(4,1,'[1173, 1547, 3037, 3376]'),(5,3,'[1173, 1547, 3037, 3376, 1090, 1736, 2350, 2757, 3650, 4415, 4729]'),(6,3,'[1173, 1547, 3037, 3376, 1090, 1736, 2350, 2757, 3650, 4415, 4729]');
/*!40000 ALTER TABLE `rhythm_node2` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rhythm_node3`
--

DROP TABLE IF EXISTS `rhythm_node3`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rhythm_node3` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `music_id` int(11) NOT NULL,
  `rhythm_time` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rhythm_node3`
--

LOCK TABLES `rhythm_node3` WRITE;
/*!40000 ALTER TABLE `rhythm_node3` DISABLE KEYS */;
INSERT INTO `rhythm_node3` VALUES (1,1,'[2435, 3267, 3795, 4819, 5043, 5579, 6371, 7132, 7811, 8571, 9580, 10307]'),(2,1,'[3206, 5030, 7518, 11023, 16662, 18134, 18926, 19942, 20798, 21678, 22494, 23342, 25327, 26542, 29094, 29302, 30270, 31503, 32798, 34166]'),(3,3,'[5605, 6521, 8823, 11181, 12067, 13715, 14650]'),(4,1,'[1294, 1594, 2920]'),(5,3,'[1294, 1594, 2920, 1257, 2242, 2618, 3764]'),(6,3,'[1294, 1594, 2920, 1257, 2242, 2618, 3764]');
/*!40000 ALTER TABLE `rhythm_node3` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rhythm_node4`
--

DROP TABLE IF EXISTS `rhythm_node4`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rhythm_node4` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `music_id` int(11) NOT NULL,
  `rhythm_time` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rhythm_node4`
--

LOCK TABLES `rhythm_node4` WRITE;
/*!40000 ALTER TABLE `rhythm_node4` DISABLE KEYS */;
INSERT INTO `rhythm_node4` VALUES (1,1,'[2115, 2755, 3051, 3587, 5371, 6739, 7459, 8163, 9227, 9963]'),(2,1,'[2342, 5878, 7094, 10206, 12742, 15270, 16894, 18326, 19117, 20118, 21006, 21846, 22686, 23526, 25534, 27086, 27742, 28398, 30054, 31910, 33007, 33974]'),(3,3,'[4681, 7915, 10218, 11831, 13081, 13939]'),(4,1,'[1368, 1685, 2153, 2368, 2694]'),(5,3,'[1368, 1685, 2153, 2368, 2694, 1583, 1832, 2421, 2850, 3016, 4106]'),(6,3,'[1368, 1685, 2153, 2368, 2694, 1583, 1832, 2421, 2850, 3016, 4106]');
/*!40000 ALTER TABLE `rhythm_node4` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-12-12 14:36:30

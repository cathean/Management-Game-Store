-- MySQL dump 10.16  Distrib 10.1.38-MariaDB, for Win32 (AMD64)
--
-- Host: kurawa.dnsbit.net    Database: kodingan_gamestore
-- ------------------------------------------------------
-- Server version	10.4.12-MariaDB

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
-- Table structure for table `acara_diskon`
--

DROP TABLE IF EXISTS `acara_diskon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `acara_diskon` (
  `id_diskon` int(4) NOT NULL AUTO_INCREMENT,
  `persentase` float DEFAULT NULL,
  `nama_acara` varchar(20) DEFAULT NULL,
  `tanggal_mulai` date DEFAULT NULL,
  `tanggal_akhir` date DEFAULT NULL,
  PRIMARY KEY (`id_diskon`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `acara_diskon`
--

LOCK TABLES `acara_diskon` WRITE;
/*!40000 ALTER TABLE `acara_diskon` DISABLE KEYS */;
/*!40000 ALTER TABLE `acara_diskon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `Id_admin` int(4) NOT NULL AUTO_INCREMENT,
  `Nama` varchar(20) DEFAULT NULL,
  `Kontak` varchar(15) DEFAULT NULL,
  `username` varchar(15) DEFAULT NULL,
  `password` varchar(15) DEFAULT NULL,
  `tipe` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`Id_admin`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (2,'Roa','roa@gmail.com','roachan','niji','admin'),(3,'Hatsune Miku','miku@gmail.com','Hatsune Miku','miku123','admin'),(5,'SuperAdmin','SuperAdmin','SuperAdmin','adminanjing','admin'),(7,'Android Bin Laden','andro@gmail.com','Android','123','admin'),(9,'root','root','root','root','admin');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detail_produk`
--

DROP TABLE IF EXISTS `detail_produk`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `detail_produk` (
  `id_detail_produk` int(4) NOT NULL AUTO_INCREMENT,
  `total_harga` float DEFAULT NULL,
  `kuantitas_game` int(8) DEFAULT NULL,
  `id_game` bigint(20) DEFAULT NULL,
  `id_pesanan` int(4) DEFAULT NULL,
  PRIMARY KEY (`id_detail_produk`),
  KEY `id_game_fk` (`id_game`),
  KEY `id_pemesanan` (`id_pesanan`),
  CONSTRAINT `id_game_fk` FOREIGN KEY (`id_game`) REFERENCES `game` (`id_game`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `id_pemesanan` FOREIGN KEY (`id_pesanan`) REFERENCES `pemesanan` (`id_pesanan`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detail_produk`
--

LOCK TABLES `detail_produk` WRITE;
/*!40000 ALTER TABLE `detail_produk` DISABLE KEYS */;
/*!40000 ALTER TABLE `detail_produk` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `game`
--

DROP TABLE IF EXISTS `game`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `game` (
  `id_game` bigint(20) NOT NULL,
  `harga` float DEFAULT NULL,
  `nama_game` varchar(50) DEFAULT NULL,
  `deskripsi` varchar(2000) DEFAULT NULL,
  `genre` varchar(200) DEFAULT NULL,
  `platform` varchar(200) DEFAULT NULL,
  `pajak` float DEFAULT NULL,
  `url` varchar(120) DEFAULT NULL,
  `id_diskon` int(4) DEFAULT NULL,
  PRIMARY KEY (`id_game`),
  KEY `id_diskon` (`id_diskon`),
  CONSTRAINT `id_diskon` FOREIGN KEY (`id_diskon`) REFERENCES `acara_diskon` (`id_diskon`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `game`
--

LOCK TABLES `game` WRITE;
/*!40000 ALTER TABLE `game` DISABLE KEYS */;
INSERT INTO `game` VALUES (-1374693881,23000,'Payday 2','PAYDAY 2 is a four-player co-op shooter that has you sporting the masks of the original PAYDAY gang – Dallas, Hoxton, Wolf and Chains – as they hit on Washington D.C. for an epic crime rampage. The new CRIMENET network offers a vast array of dynamic contracts - choose anything from PAYDAY 2 is a four-player co-op shooter that has you sporting the masks of the original PAYDAY gang – Dallas, Hoxton, Wolf and Chains – as they hit on Washington D.C. for an epic crime rampage. The new CRIMENET network offers a vast array of dynamic contracts - choose anything from small-time store rips, cyber-crime or major bank vaults for that lucrative PAYDAY.  &hellip;  Expand','Action,Shooter,Shooter,First-Person,Fantasy,General,Fantasy,Arcade','PlayStation 3,PlayStation 4,Switch,Xbox 360,Xbox One',10000,'https://static.metacritic.com/images/products/games/0/0be79b1606c68fefd5c49b5bab582ffc-98.jpg',NULL),(-229032560,250000,'God Eater Resurrection','Confronting an enemy completely immune to conventional weapons, modern society is driven to the point of extinction. One last hope remains. Following the Fenrir Organization’s development of God Arcs, their wielders are organized into an elite force of God Eaters. As the newest recruit to Confronting an enemy completely immune to conventional weapons, modern society is driven to the point of extinction. One last hope remains. Following the Fenrir Organization’s development of God Arcs, their wielders are organized into an elite force of God Eaters. As the newest recruit to Fenrir’s Far East Branch, discover that in times of adversity, humans will go to any length to survive in God Eater Resurrection. [Playstation.com] &hellip;  Expand','Role-Playing,Action RPG','PC,PlayStation Vita',25000,'https://static.metacritic.com/images/products/games/7/ee5f93b8c7661ffe4609bda70322f9cd-98.jpg',NULL),(27627587,190000,'The Elder Scrolls V: Skyrim','The next chapter in the Elder Scrolls saga arrives from the Bethesda Game Studios. Skyrim reimagines the open-world fantasy epic, bringing to life a complete virtual world open for you to explore any way you choose. Play any type of character you can imagine, and do whatever you want; the The next chapter in the Elder Scrolls saga arrives from the Bethesda Game Studios. Skyrim reimagines the open-world fantasy epic, bringing to life a complete virtual world open for you to explore any way you choose. Play any type of character you can imagine, and do whatever you want; the legendary freedom of choice, storytelling, and adventure of The Elder Scrolls is realized like never before. Skyrim\'s new game engine brings to life a complete virtual world with rolling clouds, rugged mountains, bustling cities, lush fields, and ancient dungeons. Choose from hundreds of weapons, spells, and abilities. The new character system allows you to play any way you want and define yourself through your actions. Battle ancient dragons like you\'ve never seen. As Dragonborn, learn their secrets and harness their power for yourself. &hellip;  Expand','Role-Playing,First-Person,First-Person,Western-Style','PlayStation 3,Xbox 360',10000,'https://static.metacritic.com/images/products/games/7/5988ee04196a686e107b46174f94a3ae-98.jpg',NULL),(845659955,120000,'Total War: WARHAMMER II','Prince Tyrion, Defender of Ulthuan, guides the High Elves in their desperate efforts to stabilise the vortex as it roils above their home continent. Atop his palanquin-throne, the Slann Mage-Priest Mazdamundi directs his Lizardmen war-hosts as they surge northward from Lustria. He, too, is Prince Tyrion, Defender of Ulthuan, guides the High Elves in their desperate efforts to stabilise the vortex as it roils above their home continent. Atop his palanquin-throne, the Slann Mage-Priest Mazdamundi directs his Lizardmen war-hosts as they surge northward from Lustria. He, too, is intent on preventing cataclysm, though the methods of The Old Ones must prevail. The Witch King Malekith and his sadistic Dark Elf hordes spew forth from Naggaroth and their labyrinthine Black Arks. He tastes great weakness in the vortex -- and great opportunity in its demise. Meanwhile a fourth, secretive race stirs, their motives obscured by sinister plots and machinations. The time for revelation is nigh... The second in a trilogy and sequel to the award-winning Total War: WARHAMMER, Total War: WARHAMMER II brings players a breathtaking new narrative campaign, set across the vast continents of Lustria, Ulthuan, Naggaroth and the Southlands. The Great Vortex Campaign builds pace to culminate in a definitive and climactic endgame, an experience unlike any other Total War title to date. Playing as one of 8 Legendary Lords across 4 iconic races from the world of Warhammer Fantasy Battles, players must succeed in performing a series of powerful arcane rituals in order to stabilise or disrupt The Great Vortex, while foiling the progress of the other races. Each Legendary Lord has a unique geographical starting position, and each race offers a distinctive new playstyle with unique campaign mechanics, narrative, methods of war, armies, monsters, Lores of Magic, legendary characters, and staggering new battlefield bombardment abilities. &hellip;  Expand','Strategy,Real-Time,Tactics','',3000,'https://static.metacritic.com/images/products/games/6/218db80201f6501e4760872a6adaf5e7-98.jpg',NULL),(863271227,100000,'ArmA II','Building on 10 years of constant engine development, ARMA II boasts the most realistic combat environment in the world. It models real world ballistics & round deflection, materials penetration, features a realtime day/night cycle and dynamic wind, weather and environmental effects. The Building on 10 years of constant engine development, ARMA II boasts the most realistic combat environment in the world. It models real world ballistics & round deflection, materials penetration, features a realtime day/night cycle and dynamic wind, weather and environmental effects. The simulation of a combat environment is so effective, the engine forms the basis for training simulators used by real armies the world over. Although ArmA II is set in the fictional ex-soviet state of \'Chernarus\' the gameworld is actually a 225 square kilometer chunk of the real world! ARMA II\'s highly detailed landscape is a meticulous facsimile of real terrain, modeled using extensive geographical data. This recreated region is brought to life with spectacular environmental effects and populated with dynamic civilian settlements and wildlife. Wild animals roam the atmospheric forests while the people of Chernarus try to live out their lives among the war-torn streets. The 27th U.S. Marine Expeditionary Unit have been deployed to the former soviet country of Chernarus in this third installment in the series of award winning PC war simulators from Bohemia Interactive, creators of Operation Flashpoint: Cold War Crisis and ArmA: Combat Operations. Force Reconnaissance Team \"Razor\" are among the first to fight. This elite five-man team are about to fall down the rabbit hole, trapped in a war not only for control of the country, but the hearts and minds of its people. With the might of the USMC offshore and the Russians anxiously watching from the north, the stakes couldn\'t be higher. The fate of Chernarus is balanced on a razor\'s edge. [Steam] &hellip;  Expand','Action,Shooter,Shooter,First-Person,Modern,Modern,Arcade','',10000,'https://static.metacritic.com/images/products/games/2/9288a6466cb95f40ff94bcf11699ee37-98.jpg',NULL),(1799995693,330000,'Just Cause 3','Set in the bucolic Mediterranean island republic of Medici, a land crumbling under the brutal rule of General Di Ravello, you assume control of franchise\'s hero Rico Rodriguez, a man on a mission to liberate his homeland and the people of Medici...by any means necessary. With over four Set in the bucolic Mediterranean island republic of Medici, a land crumbling under the brutal rule of General Di Ravello, you assume control of franchise\'s hero Rico Rodriguez, a man on a mission to liberate his homeland and the people of Medici...by any means necessary. With over four hundred square miles of utter freedom from sky to seabed and a massive arsenal of weaponry, gadgets and vehicles, prepare to unleash chaos in the most creative and explosive ways you can dream up. &hellip;  Expand','Sci-Fi,Action Adventure,Open-World','PlayStation 4,Xbox One',10000,'https://static.metacritic.com/images/products/games/9/d4aaca242343c92db284d7039112f788-98.jpg',NULL);
/*!40000 ALTER TABLE `game` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `game_code`
--

DROP TABLE IF EXISTS `game_code`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `game_code` (
  `id_game_code` int(4) NOT NULL AUTO_INCREMENT,
  `game_code` varchar(30) CHARACTER SET latin1 DEFAULT NULL,
  `id_game` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_game_code`),
  KEY `id_game` (`id_game`),
  CONSTRAINT `id_game` FOREIGN KEY (`id_game`) REFERENCES `game` (`id_game`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `game_code`
--

LOCK TABLES `game_code` WRITE;
/*!40000 ALTER TABLE `game_code` DISABLE KEYS */;
INSERT INTO `game_code` VALUES (36,'JC3',1799995693),(42,'TE200',27627587),(47,'BBBBBBBBBB',-229032560),(48,'CCCCCCCCCC',-229032560),(50,'YYYYYYYYYYYY',-1374693881),(51,'ABJKSD',863271227),(52,'BBBVVVCCCC',845659955);
/*!40000 ALTER TABLE `game_code` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jenis_pembayaran`
--

DROP TABLE IF EXISTS `jenis_pembayaran`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jenis_pembayaran` (
  `id_pembayaran` int(4) NOT NULL AUTO_INCREMENT,
  `jenis` varchar(20) DEFAULT NULL,
  `no_rek` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_pembayaran`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jenis_pembayaran`
--

LOCK TABLES `jenis_pembayaran` WRITE;
/*!40000 ALTER TABLE `jenis_pembayaran` DISABLE KEYS */;
INSERT INTO `jenis_pembayaran` VALUES (6,'BNI',33451123),(7,'BRI',78512332);
/*!40000 ALTER TABLE `jenis_pembayaran` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pembeli`
--

DROP TABLE IF EXISTS `pembeli`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pembeli` (
  `id_pembeli` int(4) NOT NULL AUTO_INCREMENT,
  `nama` varchar(20) DEFAULT NULL,
  `email` varchar(20) DEFAULT NULL,
  `id_pembayaran` int(4) DEFAULT NULL,
  PRIMARY KEY (`id_pembeli`),
  KEY `id_pembayaran` (`id_pembayaran`),
  CONSTRAINT `id_pembeli_fk` FOREIGN KEY (`id_pembayaran`) REFERENCES `jenis_pembayaran` (`id_pembayaran`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pembeli`
--

LOCK TABLES `pembeli` WRITE;
/*!40000 ALTER TABLE `pembeli` DISABLE KEYS */;
INSERT INTO `pembeli` VALUES (22,'Bayu','bayu@gmail.com',6),(23,'','',6),(24,'Bayu','bayu@gmail.com',6),(25,'nopal','nopal@gmail.com',6);
/*!40000 ALTER TABLE `pembeli` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pemesanan`
--

DROP TABLE IF EXISTS `pemesanan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pemesanan` (
  `id_pesanan` int(4) NOT NULL AUTO_INCREMENT,
  `tgl_pembelian` datetime DEFAULT NULL,
  `nama_pembeli` varchar(20) DEFAULT NULL,
  `email` varchar(20) DEFAULT NULL,
  `nama_karyawan` varchar(20) DEFAULT NULL,
  `jumlah_harga` float DEFAULT NULL,
  `metode` varchar(20) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `id_admin` int(4) DEFAULT NULL,
  `id_pembeli` int(4) DEFAULT NULL,
  PRIMARY KEY (`id_pesanan`),
  KEY `id_pembeli` (`id_pembeli`),
  KEY `id_admin` (`id_admin`),
  CONSTRAINT `id_admin` FOREIGN KEY (`id_admin`) REFERENCES `admin` (`Id_admin`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `id_pembeli` FOREIGN KEY (`id_pembeli`) REFERENCES `pembeli` (`id_pembeli`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pemesanan`
--

LOCK TABLES `pemesanan` WRITE;
/*!40000 ALTER TABLE `pemesanan` DISABLE KEYS */;
/*!40000 ALTER TABLE `pemesanan` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-08-07 10:15:29

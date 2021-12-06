-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: bsm
-- ------------------------------------------------------
-- Server version	8.0.18

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `authorize`
--

DROP TABLE IF EXISTS `authorize`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `authorize` (
  `authorizeid` varchar(64) NOT NULL,
  `pageid` varchar(64) NOT NULL,
  `rolename` varchar(64) NOT NULL,
  `pagepath` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`authorizeid`),
  KEY `authorize_role_roleId_fk` (`rolename`),
  KEY `authorize_pages_pageKey_fk` (`pageid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authorize`
--

LOCK TABLES `authorize` WRITE;
/*!40000 ALTER TABLE `authorize` DISABLE KEYS */;
INSERT INTO `authorize` VALUES ('01981653-e655-45bc-80d0-9bbd9ef8f476','a810a466-3801-11ec-8ae5-b025aa391cae','vip','/xxxg'),('03877208-5d87-417d-8ec4-46e0044a87d6','fc75d468-3803-11ec-8ae5-b025aa391cae','user','/dsrw'),('04c4f27b-df71-42bb-bd1d-d3d86496e3c7','37de5cb9-37fe-11ec-8ae5-b025aa391cae','admin','/qxgl'),('0730ea7f-9537-4d72-8982-a1b3109e7050','1e176d84-37ff-11ec-8ae5-b025aa391cae','user','/tslb'),('07c6e8d0-2356-461b-b385-b25e2c5baf0a','e5e2cb20-3801-11ec-8ae5-b025aa391cae','user','/grzx'),('097dd5e6-e660-4848-b684-5bd74aac8c84','9b42606b-3803-11ec-8ae5-b025aa391cae','user','/rzjk'),('0d67bcd1-5806-4a13-90ca-861571c96352','c623e450-37fc-11ec-8ae5-b025aa391cae','superAdmin',NULL),('11773b1b-2cc8-4299-b249-e4b4af2fb63e','e9cdc0af-3802-11ec-8ae5-b025aa391cae','admin','/sjkbf'),('12b99876-bc84-4b80-ba1a-f87030c79fd8','f58ef98d-3803-11ec-8ae5-b025aa391cae','guest','/pcrw'),('150a3cf2-5939-4e1c-b1e6-148f408545e2','5509a50f-3801-11ec-8ae5-b025aa391cae','guest','/rlkgl'),('19d5d2bc-82e1-443d-83ea-b9e2483f3a64','c623e450-37fc-11ec-8ae5-b025aa391cae','user',NULL),('1a5d92ef-cafc-4b56-ab08-c335c5982149','6d2d15bd-37ff-11ec-8ae5-b025aa391cae','vip','/tpsb'),('1c2e6dd0-428c-4f4f-ad4e-d95dca7fe812','37de5cb9-37fe-11ec-8ae5-b025aa391cae','guest','/qxgl'),('1ebbde66-46dd-4084-a304-470e7f53579d','a810a466-3801-11ec-8ae5-b025aa391cae','guest','/xxxg'),('2c68d5db-1e44-4399-8fec-4eb4b5d6bb0e','e3e9a184-37fe-11ec-8ae5-b025aa391cae','admin','/dzsfw'),('2cb99605-19bb-475f-b57c-04cfc4ef5f6b','90bb48fd-37fc-11ec-8ae5-b025aa391cae','superAdmin',NULL),('2d24a307-c22c-4d00-b270-bc313b82d750','10655681-37fd-11ec-8ae5-b025aa391cae','user',NULL),('2e822b95-7ce8-45ba-a291-2165fc340e62','785eb39d-37fe-11ec-8ae5-b025aa391cae','superAdmin','/cdgl'),('2f421f9d-c522-4ac4-85ad-84ea7a4998c5','9b42606b-3803-11ec-8ae5-b025aa391cae','admin','/rzjk'),('30c54b6c-9f00-40b8-acdc-cf1b0378fb09','10655681-37fd-11ec-8ae5-b025aa391cae','vip',NULL),('31679551-0d58-4a9e-9010-e208963e4078','f020b19e-37fc-11ec-8ae5-b025aa391cae','admin',NULL),('31bcc216-3313-41ed-bb53-6c0b65d51b1b','10655681-37fd-11ec-8ae5-b025aa391cae','admin',NULL),('3532cffc-9ec1-4220-b1ba-5d6eb5d93843','5509a50f-3801-11ec-8ae5-b025aa391cae','admin','/rlkgl'),('3ae1858e-8062-4882-bce8-ad41f829c58f','4e9237c5-3802-11ec-8ae5-b025aa391cae','user','/sjypz'),('3b27ebd3-33c6-4b9a-95e0-48aa9fad35a7','2eb5c25d-3801-11ec-8ae5-b025aa391cae','vip','/txzq'),('3cfc3920-4204-4169-9d3c-1ba749566e36','e9cdc0af-3802-11ec-8ae5-b025aa391cae','guest','/sjkbf'),('3da6d97d-13e3-4f62-8bfc-8bf63528fd97','2eb5c25d-3801-11ec-8ae5-b025aa391cae','admin','/txzq'),('3e3dcb37-a329-4814-979f-d30d62ab1ca4','2f988511-37fd-11ec-8ae5-b025aa391cae','vip',NULL),('43baa8a5-92d0-4d99-90ec-62100c477018','10655681-37fd-11ec-8ae5-b025aa391cae','superAdmin',NULL),('4593c564-2781-4863-bd50-8232e83e47ba','785eb39d-37fe-11ec-8ae5-b025aa391cae','guest','/cdgl'),('4a3a50fe-13a0-49f2-bde7-4b323ca89523','931fafdd-3803-11ec-8ae5-b025aa391cae','admin','/pzxgl'),('52ef5fb5-e960-4dc5-9638-6fbf048a6608','e3e9a184-37fe-11ec-8ae5-b025aa391cae','guest','/dzsfw'),('543a8b53-766b-4f7c-85fc-e40342c6444d','e3e9a184-37fe-11ec-8ae5-b025aa391cae','vip','/dzsfw'),('54c07f40-9152-441e-a7c3-52f9b8a06e8b','931fafdd-3803-11ec-8ae5-b025aa391cae','superAdmin','/pzxgl'),('553fba14-6e40-4364-9281-def2ccf5568b','88513991-3802-11ec-8ae5-b025aa391cae','superAdmin','/sjcq'),('55ba3d29-b16b-499d-a22a-d5171a9cf16f','5509a50f-3801-11ec-8ae5-b025aa391cae','superAdmin','/rlkgl'),('561d250a-7750-4be9-a44a-e4ac97e86274','6d2d15bd-37ff-11ec-8ae5-b025aa391cae','guest','/tpsb'),('58ef4173-c4d0-4501-bf70-ea7d1d468a7d','e5e2cb20-3801-11ec-8ae5-b025aa391cae','admin','/grzx'),('59379c39-99da-4a6c-aa67-db50999d72da','ceb0ca21-37fd-11ec-8ae5-b025aa391cae','guest','/yhgl'),('599a672d-f448-4fe7-8cbb-36fffa6a2d2a','ceb0ca21-37fd-11ec-8ae5-b025aa391cae','vip','/yhgl'),('5a2ee052-6387-462a-8e26-684bb18bcc71','1e176d84-37ff-11ec-8ae5-b025aa391cae','superAdmin','/tslb'),('5a940a83-4c87-481e-8c1e-412dd69d9ccb','fc75d468-3803-11ec-8ae5-b025aa391cae','admin','/dsrw'),('5adf1b47-9582-4bc6-9f97-7838ce08aa4a','2f988511-37fd-11ec-8ae5-b025aa391cae','superAdmin',NULL),('5cffd0cd-96a3-4fa2-84d5-46d8fd930f2d','37de5cb9-37fe-11ec-8ae5-b025aa391cae','user','/qxgl'),('5e2e6965-119a-4b37-8eff-343b273a38de','7f057f2f-37fd-11ec-8ae5-b025aa391cae','superAdmin',NULL),('5e8e528b-ab2f-43f8-940b-f4979a418440','08e5ecc6-37fe-11ec-8ae5-b025aa391cae','admin','/jsgl'),('63deee80-ba4d-41ac-a154-db9cbffc4ead','9b42606b-3803-11ec-8ae5-b025aa391cae','superAdmin','/rzjk'),('66b7345c-2f9f-499c-9173-dbfc68a6dd29','2f988511-37fd-11ec-8ae5-b025aa391cae','admin',NULL),('67f76780-a39d-40c1-bce3-854709d4925b','88513991-3802-11ec-8ae5-b025aa391cae','admin','/sjcq'),('6863cb5a-f479-43cd-8e1a-8572c88db332','6d2d15bd-37ff-11ec-8ae5-b025aa391cae','admin','/tpsb'),('686b80ed-c49b-4551-a80e-82994fb12005','785eb39d-37fe-11ec-8ae5-b025aa391cae','vip','/cdgl'),('6cdb7753-db8a-4ece-9660-95d7bed1171a','4e9237c5-3802-11ec-8ae5-b025aa391cae','guest','/sjypz'),('6fc701c6-49f9-4765-a629-4e2c10f2840f','9ee3a4d6-37fe-11ec-8ae5-b025aa391cae','superAdmin','/tsgl'),('70adeb5d-3482-4355-b0eb-959cba800373','ceb0ca21-37fd-11ec-8ae5-b025aa391cae','admin','/yhgl'),('71d97d68-29bb-473c-8e08-6c90a78cff56','f020b19e-37fc-11ec-8ae5-b025aa391cae','user',NULL),('723cf2bf-7318-4ef1-9f74-d2680cbbca46','6d2d15bd-37ff-11ec-8ae5-b025aa391cae','user','/tpsb'),('72b86421-61f4-4474-b511-0594cbfe0c90','f58ef98d-3803-11ec-8ae5-b025aa391cae','vip','/pcrw'),('73499f42-f9c8-46d2-8de4-d103f6bcd30f','f58ef98d-3803-11ec-8ae5-b025aa391cae','superAdmin','/pcrw'),('73a06804-23eb-40ea-9783-00e7819a3690','615de747-37fd-11ec-8ae5-b025aa391cae','superAdmin',NULL),('748ff467-722f-46eb-aa2f-2cbd31cc287a','9ee3a4d6-37fe-11ec-8ae5-b025aa391cae','admin','/tsgl'),('773aa5a9-ba37-421a-aa80-1409cc8b7c83','615de747-37fd-11ec-8ae5-b025aa391cae','user',NULL),('7801094d-fbcb-4e28-9525-fcdb0ab2516c','9ee3a4d6-37fe-11ec-8ae5-b025aa391cae','user','/tsgl'),('79383e8d-795c-4da4-9b34-5e1c8c5271b5','90bb48fd-37fc-11ec-8ae5-b025aa391cae','user',NULL),('7ab28a3b-af5b-406e-bd9f-c74a376251d9','785eb39d-37fe-11ec-8ae5-b025aa391cae','admin','/cdgl'),('7b35dd3a-0458-4804-9346-967372ce1086','2eb5c25d-3801-11ec-8ae5-b025aa391cae','superAdmin','/txzq'),('7cc5f63b-4ee9-4363-b8c2-dcbd21a5ca24','c623e450-37fc-11ec-8ae5-b025aa391cae','guest',NULL),('7d51127c-1bf1-4f22-8742-0331b86b4d67','c623e450-37fc-11ec-8ae5-b025aa391cae','vip',NULL),('7e4e7f9e-7c8d-4a30-ad5c-90bf192a71d5','a810a466-3801-11ec-8ae5-b025aa391cae','superAdmin','/xxxg'),('7fb18960-9516-42d2-969e-190d6b042f0d','5509a50f-3801-11ec-8ae5-b025aa391cae','user','/rlkgl'),('800cdb62-d514-4f30-bb8e-df3179811f64','90bb48fd-37fc-11ec-8ae5-b025aa391cae','guest',NULL),('8036867a-3b84-40e6-ab7d-5797a367c87e','fc75d468-3803-11ec-8ae5-b025aa391cae','vip','/dsrw'),('854cda21-2885-45e4-bc1d-2d2cbcac25c5','e5e2cb20-3801-11ec-8ae5-b025aa391cae','superAdmin','/grzx'),('855228f7-1244-4e6b-9a61-eb4cb43a7c28','4e9237c5-3802-11ec-8ae5-b025aa391cae','admin','/sjypz'),('89390e21-9ca2-4150-935c-09ffeb8d27bf','88513991-3802-11ec-8ae5-b025aa391cae','guest','/sjcq'),('8b3b81ca-1ff7-49b6-8ad0-dc51619fa2fe','f58ef98d-3803-11ec-8ae5-b025aa391cae','admin','/pcrw'),('8b8e5d72-6383-4168-9ed7-06552d5ebe09','f020b19e-37fc-11ec-8ae5-b025aa391cae','vip',NULL),('8da34f08-e2e0-48f2-b18d-54a1d28d3429','42396068-37ff-11ec-8ae5-b025aa391cae','user','/wzsb'),('8da6c18d-51da-45f2-a6c8-a964cefb5e4b','08e5ecc6-37fe-11ec-8ae5-b025aa391cae','vip','/jsgl'),('8e0b2110-a4f8-459b-abec-9686d63b34f1','7f057f2f-37fd-11ec-8ae5-b025aa391cae','user',NULL),('8fb6d815-cd5d-44e4-9263-2fe8d1601042','e3e9a184-37fe-11ec-8ae5-b025aa391cae','user','/dzsfw'),('9106c606-2729-4b07-a3b5-abdf9c1bce75','9ee3a4d6-37fe-11ec-8ae5-b025aa391cae','guest','/tsgl'),('91a49703-c4a5-49ed-897a-e4489aa6a655','c623e450-37fc-11ec-8ae5-b025aa391cae','admin',NULL),('924d4187-4f42-4c97-881c-9ee619939298','90bb48fd-37fc-11ec-8ae5-b025aa391cae','vip',NULL),('94f9575c-768d-4e2c-9243-62a53fdc353e','7f057f2f-37fd-11ec-8ae5-b025aa391cae','vip',NULL),('951874ef-15c7-43bf-8f4b-7a2373460b1c','7f057f2f-37fd-11ec-8ae5-b025aa391cae','guest',NULL),('970480ad-e251-4f5f-9619-f4bedb27ab70','9b42606b-3803-11ec-8ae5-b025aa391cae','vip','/rzjk'),('9a2355e6-c744-43b3-b0db-4ab4d9fa39e2','fc75d468-3803-11ec-8ae5-b025aa391cae','guest','/dsrw'),('9b9a2b13-47f3-4d23-beca-ddbddedfc330','2eb5c25d-3801-11ec-8ae5-b025aa391cae','user','/txzq'),('9c99f772-c6c1-41d9-a9d1-58c1c820fb37','1e176d84-37ff-11ec-8ae5-b025aa391cae','guest','/tslb'),('9d803196-2217-44ab-a827-87f1ac30c3ba','4e9237c5-3802-11ec-8ae5-b025aa391cae','superAdmin','/sjypz'),('9e4c4d59-b23c-43de-9b2c-0041c456169d','931fafdd-3803-11ec-8ae5-b025aa391cae','guest','/pzxgl'),('9f5841b7-f435-454f-90f5-4fa7f9d4f091','88513991-3802-11ec-8ae5-b025aa391cae','user','/sjcq'),('9f659656-9478-47f2-ad57-7e11c207ee66','42396068-37ff-11ec-8ae5-b025aa391cae','vip','/wzsb'),('a3787e61-7e90-4985-ae24-267d551bdcac','1e176d84-37ff-11ec-8ae5-b025aa391cae','admin','/tslb'),('a51dbd23-9843-446b-9735-4f93e77c1384','37de5cb9-37fe-11ec-8ae5-b025aa391cae','superAdmin','/qxgl'),('a594085b-de3a-4024-8086-4757d75afef2','e9cdc0af-3802-11ec-8ae5-b025aa391cae','vip','/sjkbf'),('a6cc5a6f-5065-455d-aa87-75ab7d41bb46','4e9237c5-3802-11ec-8ae5-b025aa391cae','vip','/sjypz'),('ac7259e1-f3a6-4e0a-bf0d-e83e18e71762','e5e2cb20-3801-11ec-8ae5-b025aa391cae','guest','/grzx'),('aeab299a-8aa0-4431-a77d-da1d54a08a66','90bb48fd-37fc-11ec-8ae5-b025aa391cae','admin',NULL),('b056cac2-019f-41d0-89fb-fbff6fa26ee2','37de5cb9-37fe-11ec-8ae5-b025aa391cae','vip','/qxgl'),('b0c91143-f5aa-4283-ae22-1b4d07a410ca','e9cdc0af-3802-11ec-8ae5-b025aa391cae','superAdmin','/sjkbf'),('b149cdc5-ce94-4f4e-b85c-358e50a4b0fe','785eb39d-37fe-11ec-8ae5-b025aa391cae','user','/cdgl'),('b46bae14-2208-4e23-8aff-4ff14bea5e72','fc75d468-3803-11ec-8ae5-b025aa391cae','superAdmin','/dsrw'),('b5fdeb9d-02c1-4ff7-b749-062447479438','615de747-37fd-11ec-8ae5-b025aa391cae','vip',NULL),('bdc4d456-a47b-42b2-a7dc-cc7da470f040','08e5ecc6-37fe-11ec-8ae5-b025aa391cae','guest','/jsgl'),('bdd4f983-5873-458e-ba43-abf17105596c','08e5ecc6-37fe-11ec-8ae5-b025aa391cae','superAdmin','/jsgl'),('c072afb8-3643-40e8-855a-90f34dd88ba8','615de747-37fd-11ec-8ae5-b025aa391cae','admin',NULL),('c0b7d7ea-84a1-4151-8d0b-135a0073deb1','a810a466-3801-11ec-8ae5-b025aa391cae','user','/xxxg'),('c2329451-b0ae-47f6-a56b-a1f4427da856','1e176d84-37ff-11ec-8ae5-b025aa391cae','vip','/tslb'),('c57694b4-8f19-49f9-97af-1bc0417546d9','42396068-37ff-11ec-8ae5-b025aa391cae','superAdmin','/wzsb'),('c6549199-ac67-470e-a046-94913537e3cb','5509a50f-3801-11ec-8ae5-b025aa391cae','vip','/rlkgl'),('c70e58c4-ca2a-401f-87fc-9fffb7d61f27','9b42606b-3803-11ec-8ae5-b025aa391cae','guest','/rzjk'),('ca70077f-a3df-455f-9066-feb4d12c4ad2','ceb0ca21-37fd-11ec-8ae5-b025aa391cae','superAdmin','/yhgl'),('cbd9275d-a191-4849-9c89-a3bd8ec3ba6e','10655681-37fd-11ec-8ae5-b025aa391cae','guest',NULL),('cd8edc5a-7884-4af7-88de-7ce182d380b6','f020b19e-37fc-11ec-8ae5-b025aa391cae','guest',NULL),('d2782ccd-de8a-4346-86ae-b3e4d79d648e','931fafdd-3803-11ec-8ae5-b025aa391cae','vip','/pzxgl'),('d60571c0-838b-4e7c-8815-8db115ad53eb','42396068-37ff-11ec-8ae5-b025aa391cae','admin','/wzsb'),('d6081ae3-ebf6-4dcc-a8c0-25d51234a4de','a810a466-3801-11ec-8ae5-b025aa391cae','admin','/xxxg'),('d9481a40-f3cb-475a-b802-a59c03b14ee0','f58ef98d-3803-11ec-8ae5-b025aa391cae','user','/pcrw'),('de5958f8-b406-4986-bbbc-5ad6b03c5c63','e5e2cb20-3801-11ec-8ae5-b025aa391cae','vip','/grzx'),('dec7260b-9dde-41f5-8d7e-c450eb79184a','615de747-37fd-11ec-8ae5-b025aa391cae','guest',NULL),('e09d79f8-f126-41e9-a258-67c1438316f5','9ee3a4d6-37fe-11ec-8ae5-b025aa391cae','vip','/tsgl'),('e2459220-a57c-4a76-8243-6f9542a3fba5','f020b19e-37fc-11ec-8ae5-b025aa391cae','superAdmin',NULL),('e45566eb-3131-4995-9991-33fca126d34e','88513991-3802-11ec-8ae5-b025aa391cae','vip','/sjcq'),('e774b4f8-e528-42aa-854d-f5d72998e7fb','42396068-37ff-11ec-8ae5-b025aa391cae','guest','/wzsb'),('e8b4aa34-7045-4bae-a9fd-1f1f7c673b18','08e5ecc6-37fe-11ec-8ae5-b025aa391cae','user','/jsgl'),('e940f14e-3003-425c-b22d-6532620c0fdf','2f988511-37fd-11ec-8ae5-b025aa391cae','user',NULL),('e9b984a6-23ca-4a30-94ce-f1f1d6589d5c','ceb0ca21-37fd-11ec-8ae5-b025aa391cae','user','/yhgl'),('ed659ce9-6487-4caa-adc6-46c79e43cf4f','7f057f2f-37fd-11ec-8ae5-b025aa391cae','admin',NULL),('ede61872-bc1f-4d6c-88bc-c69aae16de29','2eb5c25d-3801-11ec-8ae5-b025aa391cae','guest','/txzq'),('f20e83ce-425e-43fc-b0de-47d9c973b679','e3e9a184-37fe-11ec-8ae5-b025aa391cae','superAdmin','/dzsfw'),('f62179a4-62f0-4700-8be6-b02bacfaad29','931fafdd-3803-11ec-8ae5-b025aa391cae','user','/pzxgl'),('f806f2f1-8c21-49f9-90d5-632423ac931d','6d2d15bd-37ff-11ec-8ae5-b025aa391cae','superAdmin','/tpsb'),('fa48d5d1-3b67-41ec-b540-1e05f94fbf48','2f988511-37fd-11ec-8ae5-b025aa391cae','guest',NULL),('fc03334d-c4f9-4ad5-a053-0c869c909bb0','e9cdc0af-3802-11ec-8ae5-b025aa391cae','user','/sjkbf');
/*!40000 ALTER TABLE `authorize` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `config`
--

DROP TABLE IF EXISTS `config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `value` varchar(64) NOT NULL,
  `type` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config`
--

LOCK TABLES `config` WRITE;
/*!40000 ALTER TABLE `config` DISABLE KEYS */;
INSERT INTO `config` VALUES (1,'FACE_API_KEY','zp59ZHTomg8PrzKGPPHTZH5g','string'),(2,'FACE_APP_ID','21907238','string'),(3,'FACE_SECRET_KEY','hLE2H2XVOKHDhYA38XL5yw24ztrWvBCY','string'),(4,'OCR_API_KEY','1OlEjVz6Zck7h4kdCpSu2GDX','string'),(5,'OCR_APP_ID','18595904','string'),(6,'OCR_SECRET_KEY','1gylrnGUjKdUbFSqUyGd54LwUCCUStno','string'),(7,'GITEE_ACCESS_TOKEN','7c43a11a7a56ccf59945cc11f676329f','string');
/*!40000 ALTER TABLE `config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pages`
--

DROP TABLE IF EXISTS `pages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pages` (
  `pageid` varchar(64) NOT NULL,
  `pagekey` varchar(16) NOT NULL,
  `parentkey` varchar(16) NOT NULL,
  `pagepath` varchar(64) DEFAULT NULL,
  `title` varchar(64) NOT NULL,
  `icontype` varchar(16) DEFAULT NULL,
  `icon` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`pagekey`),
  UNIQUE KEY `pageId` (`pageid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pages`
--

LOCK TABLES `pages` WRITE;
/*!40000 ALTER TABLE `pages` DISABLE KEYS */;
INSERT INTO `pages` VALUES ('785eb39d-37fe-11ec-8ae5-b025aa391cae','cdgl','htgl','/cdgl','菜单管理','antd',NULL),('fc75d468-3803-11ec-8ae5-b025aa391cae','dsrw','rwgl','/dsrw','定时任务','antd',NULL),('e3e9a184-37fe-11ec-8ae5-b025aa391cae','dzsfw','tszq','/dzsfw','电子书服务','antd',NULL),('e5e2cb20-3801-11ec-8ae5-b025aa391cae','grzx','yhzx','/grzx','个人中心','antd',NULL),('c623e450-37fc-11ec-8ae5-b025aa391cae','htgl','0','/htgl','后台管理','fa','SettingOutlined'),('08e5ecc6-37fe-11ec-8ae5-b025aa391cae','jsgl','htgl','/jsgl','角色管理','antd',NULL),('f58ef98d-3803-11ec-8ae5-b025aa391cae','pcrw','rwgl','/pcrw','爬虫任务','antd',NULL),('931fafdd-3803-11ec-8ae5-b025aa391cae','pzxgl','ywgl','/pzxgl','配置项管理','antd',NULL),('37de5cb9-37fe-11ec-8ae5-b025aa391cae','qxgl','htgl','/qxgl','权限管理','antd',NULL),('5509a50f-3801-11ec-8ae5-b025aa391cae','rlkgl','znzq','/rlkgl','人脸库管理','antd',NULL),('615de747-37fd-11ec-8ae5-b025aa391cae','rwgl','0','/rwgl','任务管理','fa','CodeSandboxOutlined'),('9b42606b-3803-11ec-8ae5-b025aa391cae','rzjk','ywgl','/rzjk','日志监控','antd',NULL),('88513991-3802-11ec-8ae5-b025aa391cae','sjcq','sjgl','/sjcq','数据抽取','antd',NULL),('7f057f2f-37fd-11ec-8ae5-b025aa391cae','sjgl','0','/sjgl','数据管理','fa','CodeSandboxOutlined'),('e9cdc0af-3802-11ec-8ae5-b025aa391cae','sjkbf','sjgl','/sjkbf','数据库备份','antd',NULL),('4e9237c5-3802-11ec-8ae5-b025aa391cae','sjypz','sjgl','/sjypz','数据源配置','antd',NULL),('6d2d15bd-37ff-11ec-8ae5-b025aa391cae','tpsb','znzq','/tpsb','图片识别','antd',NULL),('9ee3a4d6-37fe-11ec-8ae5-b025aa391cae','tsgl','tszq','/tsgl','图书管理','antd',NULL),('1e176d84-37ff-11ec-8ae5-b025aa391cae','tslb','tszq','/tslb','图书类别','antd',NULL),('90bb48fd-37fc-11ec-8ae5-b025aa391cae','tszq','0','/tszq','图书专区','fa','CodeSandboxOutlined'),('2eb5c25d-3801-11ec-8ae5-b025aa391cae','txzq','znzq','/txzq','图像增强','antd',NULL),('42396068-37ff-11ec-8ae5-b025aa391cae','wzsb','znzq','/wzsb','文字识别','antd',NULL),('a810a466-3801-11ec-8ae5-b025aa391cae','xxxg','yhzx','/xxxg','信息修改','antd',NULL),('ceb0ca21-37fd-11ec-8ae5-b025aa391cae','yhgl','htgl','/yhgl','用户管理','antd',NULL),('10655681-37fd-11ec-8ae5-b025aa391cae','yhzx','0','/yhzx','用户中心','fa','TeamOutlined'),('2f988511-37fd-11ec-8ae5-b025aa391cae','ywgl','0','/ywgl','运维管理','fa','TeamOutlined'),('f020b19e-37fc-11ec-8ae5-b025aa391cae','znzq','0','/znzq','智能专区','fa','CodeSandboxOutlined');
/*!40000 ALTER TABLE `pages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persistent_logins`
--

DROP TABLE IF EXISTS `persistent_logins`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `persistent_logins` (
  `username` varchar(64) NOT NULL,
  `series` varchar(64) NOT NULL,
  `token` varchar(64) NOT NULL,
  `last_used` timestamp NOT NULL,
  PRIMARY KEY (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persistent_logins`
--

LOCK TABLES `persistent_logins` WRITE;
/*!40000 ALTER TABLE `persistent_logins` DISABLE KEYS */;
INSERT INTO `persistent_logins` VALUES ('gzc','4w8H7JxAafa6EWbMQhkztQ==','hacFXaLQKDEHVVtYHGxsBw==','2021-11-11 18:40:23'),('gzc','6m3I2sAq9DaPszGCGyGz4g==','DijSpttR5GkRvtmbxZj5vQ==','2021-11-14 00:52:04'),('gzc','nKG9SIDQh+aRjTdPIKtBQg==','GkcluRsy/2davbOLxdER7w==','2021-11-01 00:35:00'),('gzc','rkwlooftoWIbQnqSOohKxA==','/tzrn5Nf/gSlKHa/Jlr6/Q==','2021-11-01 00:43:49'),('GZC123','TQ3VHKalrJIuCF2tsjFlAA==','wZBIX/vGRu308/Mcz+6zcQ==','2021-11-28 05:35:05'),('gzc','ZEnYThPmBwbMaRtgD8yr+Q==','vpl5qyThXfxGzU4QWaIolQ==','2021-11-28 00:17:47');
/*!40000 ALTER TABLE `persistent_logins` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `remark` varchar(32) DEFAULT NULL,
  `disabled` tinyint(1) DEFAULT '0',
  `roleid` int(11) NOT NULL AUTO_INCREMENT,
  `rolename` varchar(64) NOT NULL,
  `rolecname` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`roleid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES ('拥有所有权限的角色',0,1,'superAdmin','超级管理员'),('拥有管理员权限的角色',0,2,'admin','管理员'),('拥有VIP权限的角色',0,3,'vip','会员'),('拥有普通用户的角色',0,4,'user','普通用户'),('拥有访客用户的角色',0,5,'guest','访客'),('这个是测试用户角色',1,6,'test','测试角色');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `salt` varchar(64) DEFAULT NULL,
  `enabled` tinyint(1) NOT NULL DEFAULT '0',
  `userid` varchar(64) NOT NULL,
  `password` varchar(64) NOT NULL,
  `createtime` datetime NOT NULL,
  `lastmodifytime` datetime DEFAULT NULL,
  `roleid` int(11) DEFAULT NULL,
  `usericon` varchar(128) DEFAULT NULL,
  `isfacevalid` tinyint(1) NOT NULL DEFAULT '0',
  `username` varchar(64) NOT NULL,
  `emailaddress` varchar(64) NOT NULL,
  PRIMARY KEY (`userid`),
  UNIQUE KEY `user_userId_uindex` (`userid`),
  KEY `user_role_roleId_fk` (`roleid`),
  CONSTRAINT `user_role_roleId_fk` FOREIGN KEY (`roleid`) REFERENCES `role` (`roleid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('bUE6NiZzamxkSkA/fHpEfEg+Nm9Wanw6U3RLJTE0QWs=',1,'21da04bf-809c-4022-99a3-7b7dcead4ca1','mA/FRJDlAH0SSC0Vpb3cisXA==','2021-11-19 18:59:52','2021-11-19 18:59:53',4,NULL,0,'gzc','1739084007@qq.com'),('RE9ReCdeOnw=',0,'93f6bccc-b0d2-4602-91a2-a12ded60dd39','DO6ZMoichK46+tzITOAFhb0A==','2020-02-05 13:30:41','2021-11-04 21:23:19',4,NULL,0,'admin','1739084008@qq.com'),('Vm5taXtFUFRNOlsuRiMiWTl1QU1DKGEvXmB7ODk6QXA=',1,'c930b572-fd33-428f-8c88-7eed3f3d0fb0','VnrWQfxQE6vAJj2/jGgnf+wA==','2020-02-05 13:30:41','2021-11-03 15:40:22',3,NULL,0,'HelloWorld','17366192087@163.com'),('a21qMWJaNyo=',1,'f89062aa-8833-41d6-a70a-0e40bdb8a2f3','kmy2AL8Xl9kPkXL17JAD9oPg==','2020-02-05 13:30:41','2020-02-05 13:30:41',1,NULL,0,'GZC201314','gzc201314@163.com'),('a21qMWJaNyo=',1,'f89062aa-8833-41d6-a70a-0e40bdb8a2f4','kmy2AL8Xl9kPkXL17JAD9oPg==','2020-02-05 13:30:41','2020-02-05 13:30:41',1,NULL,0,'GZC123','gzc201314@163.com');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-12-06  8:13:42

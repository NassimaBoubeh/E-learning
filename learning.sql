-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost:3307
-- Généré le : mer. 01 fév. 2023 à 02:54
-- Version du serveur : 10.4.27-MariaDB
-- Version de PHP : 8.1.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `learning`
--

-- --------------------------------------------------------

--
-- Structure de la table `boiteetud`
--

CREATE TABLE `boiteetud` (
  `id_boite` int(10) NOT NULL,
  `message` varchar(100) DEFAULT NULL,
  `fichier` varchar(100) DEFAULT NULL,
  `prof` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `boiteetud`
--

INSERT INTO `boiteetud` (`id_boite`, `message`, `fichier`, `prof`) VALUES
(1, 'bonjour mes etudiants', NULL, 2),
(2, NULL, 'C:UsersLENOVODesktopAhmed1.pdf', 2),
(3, 'demain on aura un examen', NULL, 2),
(4, 'un message pour tous', NULL, 2),
(5, NULL, 'C:UsersLENOVODesktopSemestre_3PParallèleprojet1_ressource.txt', 2);

-- --------------------------------------------------------

--
-- Structure de la table `boiteprof`
--

CREATE TABLE `boiteprof` (
  `id_boite` int(10) NOT NULL,
  `etud` int(10) NOT NULL,
  `message` varchar(100) DEFAULT NULL,
  `fichier` varchar(100) DEFAULT NULL,
  `prof` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `classe`
--

CREATE TABLE `classe` (
  `numCls` int(10) NOT NULL,
  `departement` varchar(100) NOT NULL,
  `numProf` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `classe`
--

INSERT INTO `classe` (`numCls`, `departement`, `numProf`) VALUES
(1, 'informatique', 2),
(2, 'physique', 10),
(29, 'BIOLOGIE', 23),
(30, 'COMMUNICATION', 11),
(31, 'GEOLOGIE', 23);

-- --------------------------------------------------------

--
-- Structure de la table `cls_etu`
--

CREATE TABLE `cls_etu` (
  `numCls` int(10) NOT NULL,
  `numEtu` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `cls_etu`
--

INSERT INTO `cls_etu` (`numCls`, `numEtu`) VALUES
(1, 3),
(1, 14);

-- --------------------------------------------------------

--
-- Structure de la table `personne`
--

CREATE TABLE `personne` (
  `numPer` int(10) NOT NULL,
  `login` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `nom` varchar(100) DEFAULT NULL,
  `prenom` varchar(100) DEFAULT NULL,
  `CIN` varchar(100) NOT NULL,
  `matiere` varchar(100) DEFAULT NULL,
  `type` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `personne`
--

INSERT INTO `personne` (`numPer`, `login`, `password`, `nom`, `prenom`, `CIN`, `matiere`, `type`) VALUES
(1, 'admin', '1234', NULL, NULL, '', NULL, 'admin'),
(2, 'nassima', '0000', 'nassima', 'boubeh', '', 'MATH', 'prof'),
(3, 'ahmed', '0000', 'ahmed', 'boubeh', '', NULL, 'etudiant'),
(4, 'youssef', '1478', 'youssef', 'dert', '', 'Base de données', 'prof'),
(8, 'salhi', '0000', 'salhi', 'amina', 'BE908223', NULL, 'etudiant'),
(10, 'JANDAL123', '123456', 'JANDAL', 'IMANE', 'AA38288', 'TEC', 'prof'),
(11, 'MALAK123', '123456789', 'EL HASNAOUI', 'MALAK', 'EE563195', 'BASE DE DONNEES', 'prof'),
(12, 'BENRABAH123', '0000000', 'BENRABAH', 'SOULAIMANE', 'BE582002', NULL, 'etudiant'),
(13, 'MASSAQ123', '123456', 'MASSAQ', 'HAJR', 'BJ443464', NULL, 'etudiant'),
(14, 'TAHA123', '123456789', 'TAHA', 'AMINE', 'BK289900', NULL, 'etudiant'),
(15, 'ASRI123', '0000000', 'ASRI', 'AMJAD', 'BK531000', NULL, 'etudiant'),
(16, 'CHBANI123', '123456789', 'CHBANI IDRISSI', 'NOUHAILA', 'BK626180', NULL, 'etudiant'),
(17, 'HSAINE123', 'AZERTYUIOP', 'HSAINE', 'MARIEM', 'BK604051', NULL, 'etudiant'),
(18, 'ABBAS123', 'IBTISSAM123', 'ABBAS', 'IBTISSEM', 'BK604737', NULL, 'etudiant'),
(19, 'HASLA123', 'WXCVBN', 'EL HASLA', 'ICHRAK', 'BK687755', NULL, 'etudiant'),
(20, 'JABENATTI123', 'QSDFGHJKLM', 'JABNATTI', 'ZAKARIAE', 'D928599', NULL, 'etudiant'),
(22, 'MOUTAOUKIL123', '123654789', 'MOUTAOUKIL', 'FATIMA EZZAHRA', 'I733102', NULL, 'etudiant'),
(23, '', '', 'inconnu', 'inconnu', '', NULL, '');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `boiteetud`
--
ALTER TABLE `boiteetud`
  ADD PRIMARY KEY (`id_boite`),
  ADD KEY `fkboite` (`prof`);

--
-- Index pour la table `boiteprof`
--
ALTER TABLE `boiteprof`
  ADD PRIMARY KEY (`id_boite`),
  ADD KEY `fketud` (`etud`),
  ADD KEY `fkproff` (`prof`);

--
-- Index pour la table `classe`
--
ALTER TABLE `classe`
  ADD PRIMARY KEY (`numCls`),
  ADD KEY `fk_prof` (`numProf`);

--
-- Index pour la table `cls_etu`
--
ALTER TABLE `cls_etu`
  ADD PRIMARY KEY (`numCls`,`numEtu`),
  ADD KEY `fk_etu` (`numEtu`);

--
-- Index pour la table `personne`
--
ALTER TABLE `personne`
  ADD PRIMARY KEY (`numPer`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `boiteetud`
--
ALTER TABLE `boiteetud`
  MODIFY `id_boite` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `boiteprof`
--
ALTER TABLE `boiteprof`
  MODIFY `id_boite` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `classe`
--
ALTER TABLE `classe`
  MODIFY `numCls` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT pour la table `personne`
--
ALTER TABLE `personne`
  MODIFY `numPer` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `boiteetud`
--
ALTER TABLE `boiteetud`
  ADD CONSTRAINT `fkboite` FOREIGN KEY (`prof`) REFERENCES `personne` (`numPer`);

--
-- Contraintes pour la table `boiteprof`
--
ALTER TABLE `boiteprof`
  ADD CONSTRAINT `fketud` FOREIGN KEY (`etud`) REFERENCES `personne` (`numPer`),
  ADD CONSTRAINT `fkproff` FOREIGN KEY (`prof`) REFERENCES `personne` (`numPer`);

--
-- Contraintes pour la table `classe`
--
ALTER TABLE `classe`
  ADD CONSTRAINT `fk_prof` FOREIGN KEY (`numProf`) REFERENCES `personne` (`numPer`);

--
-- Contraintes pour la table `cls_etu`
--
ALTER TABLE `cls_etu`
  ADD CONSTRAINT `fk_cls` FOREIGN KEY (`numCls`) REFERENCES `classe` (`numCls`),
  ADD CONSTRAINT `fk_etu` FOREIGN KEY (`numEtu`) REFERENCES `personne` (`numPer`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

--5 plus longs trajets de véhicules
select LieuDepart,LieuArrivee, Duree from trajet,vehicule_ where idobjettrajet = idvehicule order by duree desc FETCH NEXT 5 ROWS ONLY;

--Durées moyennes des trajets par type de véhicule pour les personnes possédant plus d'un véhicule
select IDPers, typeof, avg(duree) from trajet,vehicule_, Proprietaire_ where idvehiculep in (select idvehiculep from Proprietaire_ where idpers in (select IDPers from Proprietaire_ group by IDPers having count(*) > 1)) and idobjettrajet=idvehiculep and idvehicule=idvehiculep  group by IDPers,typeof; 

--Lieux visités par les propriétaires d'automobiles
select DISTINCT LieuArrivee from trajet where idobjettrajet in (select idpers from Proprietaire_ where idvehiculep in (select idvehicule from vehicule_ where typeof='Automobile'));

--Trajets effectués par des maitres promenant leur animal     
select distinct LieuDepart, LieuArrivee from trajet where idobjettrajet in (select idpers from Maitre_ where idanimal in (select idanimal from Animal_ where NomEspece='Chien'));

--Durée moyenne des trajets effectués par des animaux qui ont un maître
select IDAnimal, avg(duree) from animal_,trajet where IDAnimal in (select idanimal from Maitre_) and idobjettrajet=idanimal group by idanimal; 

--Durée moyenne des trajets selon le type d'objet mobile
select typeof, avg(duree) from trajet,ObjetMobile where idobjettrajet=idobjet group by typeof; 

--Liste des trajets effectués par des personnes, triée par durée moyenne des trajets
select distinct LieuDepart, LieuArrivee, avg(duree) from trajet,Personne_ where idobjettrajet=IDPers group by LieuDepart, LieuArrivee order by avg(duree) desc;

--Liste des trajets effectués par des étudiants et des professeurs
select distinct LieuDepart, LieuArrivee from trajet where idobjettrajet in (select idpers from Personne_ where Occupation='Étudiant' or Occupation = 'Professeur');

--Coordonnées des 3 destinations les plus fréquentées par les maîtres de chiens
SELECT NomLieu AS Destination, Latitude, Longitude, Frequence FROM Lieu_, (SELECT LieuArrivee, COUNT(*) AS Frequence FROM Trajet WHERE IDObjetTrajet IN (SELECT IDPers FROM Maitre_ WHERE IDAnimal IN (SELECT IDAnimal FROM Animal_ WHERE NomEspece = 'Chien')) GROUP BY LieuArrivee);

--Trajet le plus fréquent de chaque personne
WITH TRAJETS AS (SELECT IDPers, LieuDepart, LieuArrivee, COUNT(*) AS Freq FROM Trajet, Personne_ WHERE IDObjetTrajet = IDPers GROUP BY IDPers, LieuDepart, LieuArrivee) SELECT Prenom, Nom, Occupation, LieuDepart, LieuArrivee, NbParcours FROM Personne_, (SELECT TRAJETS.IDPers AS IDPers_, LieuDepart, LieuArrivee, NbParcours FROM TRAJETS, (SELECT IDPers, MAX(Freq) AS NbParcours FROM TRAJETS GROUP BY IDPers) HIGHEST_FREQ WHERE TRAJETS.IDPers = HIGHEST_FREQ.IDPers AND Freq = NbParcours) WHERE Personne_.IDPers = IDPers_ ORDER BY NbParcours DESC, Prenom, Nom, LieuDepart, LieuArrivee;
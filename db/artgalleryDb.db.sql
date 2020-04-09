BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "Age_group" (
	"id"	INTEGER PRIMARY KEY AUTOINCREMENT,
	"type"	TEXT NOT NULL,
	"from_age"	intEGER NOT NULL,
	"to_age"	INTEGER NOT NULL
);
CREATE TABLE IF NOT EXISTS "Event" (
	"id"	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	"name"	TEXT NOT NULL,
	"date"	TEXT NOT NULL,
	"venue"	TEXT NOT NULL,
	"child_rate"	INTEGER,
	"adult_rate"	INTEGER,
	"aged_rate"	INTEGER
);
CREATE TABLE IF NOT EXISTS "Registration" (
	"id"	INTEGER,
	"name"	TEXT NOT NULL,
	"address"	TEXT NOT NULL,
	"contact_no"	INTEGER NOT NULL,
	"age"	INTEGER NOT NULL,
	"event_id"	INTEGER NOT NULL,
	"confirmation_no"	TEXT,
	PRIMARY KEY("id"),
	FOREIGN KEY("event_id") REFERENCES "Event"("id")
);
CREATE TABLE IF NOT EXISTS "User" (
	"Id"	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	"Username"	TEXT NOT NULL UNIQUE,
	"Password"	TEXT NOT NULL
);
INSERT INTO "Age_group" ("id","type","from_age","to_age") VALUES (1,'child',0,0);
INSERT INTO "Age_group" ("id","type","from_age","to_age") VALUES (2,'youth',17,59);
INSERT INTO "Age_group" ("id","type","from_age","to_age") VALUES (3,'aged',60,100);
INSERT INTO "Event" ("id","name","date","venue","child_rate","adult_rate","aged_rate") VALUES (1,'test','2020-03-30','toronto',10,15,10);
INSERT INTO "Event" ("id","name","date","venue","child_rate","adult_rate","aged_rate") VALUES (2,'test2','2020-04-03','toronto',5,20,15);
INSERT INTO "Event" ("id","name","date","venue","child_rate","adult_rate","aged_rate") VALUES (3,'hgkj','2020-08-30','hbjbkj',6,20,10);
INSERT INTO "Event" ("id","name","date","venue","child_rate","adult_rate","aged_rate") VALUES (4,'hjghj','2020-08-30','hjbjbn',5,20,10);
INSERT INTO "Event" ("id","name","date","venue","child_rate","adult_rate","aged_rate") VALUES (5,'test12','2020-04-05','test',6,25,10);
INSERT INTO "Event" ("id","name","date","venue","child_rate","adult_rate","aged_rate") VALUES (6,'Art ','2020-05-01','toronto',5,22,10);
INSERT INTO "Event" ("id","name","date","venue","child_rate","adult_rate","aged_rate") VALUES (7,'test','2020-02-02','test',10,20,15);
INSERT INTO "Registration" ("id","name","address","contact_no","age","event_id","confirmation_no") VALUES (1,'sadsa','sadsad',435435,23,5,'MdsDw0PitF');
INSERT INTO "Registration" ("id","name","address","contact_no","age","event_id","confirmation_no") VALUES (2,'dfdsf','dsfdsf',435435,23,5,'snCMHUw3qu');
INSERT INTO "Registration" ("id","name","address","contact_no","age","event_id","confirmation_no") VALUES (3,'sdsad','sadsad',34534535,23,3,'J1gdRwMOet');
INSERT INTO "Registration" ("id","name","address","contact_no","age","event_id","confirmation_no") VALUES (4,'chadani','test',7856757,25,6,'eFoJv5mqJH');
INSERT INTO "User" ("Id","Username","Password") VALUES (1,'admin','admin');
COMMIT;

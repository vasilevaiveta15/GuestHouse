--user(OWNER) with everything
INSERT INTO USERS
(USERNAME,
 PASSWORD,
 ROLE)
VALUES ('test',
        '$2a$10$sQS3Hld4evFkM5CqlIFGmeUq646ybrAsfcattmqR9GtsqBfePCksO',
        'OWNER');

--user(OWNER) without individual
INSERT INTO USERS
(USERNAME,
 PASSWORD,
 ROLE)
VALUES ('test1',
        '$2a$10$sQS3Hld4evFkM5CqlIFGmeUq646ybrAsfcattmqR9GtsqBfePCksO',
        'OWNER');

--user(OWNER) without address
INSERT INTO USERS
(USERNAME,
 PASSWORD,
 ROLE)
VALUES ('test3',
        '$2a$10$sQS3Hld4evFkM5CqlIFGmeUq646ybrAsfcattmqR9GtsqBfePCksO',
        'OWNER');

--user(OWNER) with not active but add individual, phone number is empty;
INSERT INTO USERS
(USERNAME,
 PASSWORD,
 ROLE)
VALUES ('test4',
        '$2a$10$sQS3Hld4evFkM5CqlIFGmeUq646ybrAsfcattmqR9GtsqBfePCksO',
        'OWNER');

--user(RENTER) with everything
INSERT INTO USERS
(USERNAME,
 PASSWORD,
 ROLE)
VALUES ('test01',
        '$2a$10$sQS3Hld4evFkM5CqlIFGmeUq646ybrAsfcattmqR9GtsqBfePCksO',
        'RENTER');

--user(RENTER) without individual
INSERT INTO USERS
(USERNAME,
 PASSWORD,
 ROLE)
VALUES ('test02',
        '$2a$10$sQS3Hld4evFkM5CqlIFGmeUq646ybrAsfcattmqR9GtsqBfePCksO',
        'RENTER');

--user(RENTER) with not active individual
INSERT INTO USERS
(USERNAME,
 PASSWORD,
 ROLE)
VALUES ('test03',
        '$2a$10$sQS3Hld4evFkM5CqlIFGmeUq646ybrAsfcattmqR9GtsqBfePCksO',
        'RENTER');

--user(RENTER) with everything:2
INSERT INTO USERS
(USERNAME,
 PASSWORD,
 ROLE)
VALUES ('test04',
        '$2a$10$sQS3Hld4evFkM5CqlIFGmeUq646ybrAsfcattmqR9GtsqBfePCksO',
        'RENTER');

--individual with everything of user test
INSERT INTO INDIVIDUALS
(FIRST_NAME,
 MIDDLE_NAME,
 LAST_NAME,
 CARD_NUMBER,
 BIRTH_DATE,
 PHONE_NUMBER,
 EMAIL,
 IS_ACTIVE,
 USER_ID)
VALUES ('test',
        'test',
        'test',
        '4000000045739170',
        '20-04-10',
        0878152289,
        'test@abv.bg',
        0,
        (SELECT U.USER_ID
         FROM USERS U
         WHERE U.USERNAME = 'test'
        ));

--individual with everything for user without address of user test3
INSERT INTO INDIVIDUALS
(FIRST_NAME,
 MIDDLE_NAME,
 LAST_NAME,
 CARD_NUMBER,
 BIRTH_DATE,
 PHONE_NUMBER,
 EMAIL,
 IS_ACTIVE,
 USER_ID)
VALUES ('test',
        'test',
        'test',
        '4010010045739170',
        '20-04-10',
        0878152289,
        'test@abv.bg',
        0,
        (SELECT U.USER_ID
         FROM USERS U
         WHERE U.USERNAME = 'test3'
        ));

--individual with empty phone number of user test4
INSERT INTO INDIVIDUALS
(FIRST_NAME,
 MIDDLE_NAME,
 LAST_NAME,
 CARD_NUMBER,
 BIRTH_DATE,
 EMAIL,
 IS_ACTIVE,
 USER_ID)
VALUES ('test',
        'test',
        'test',
        '4000000745739170',
        '20-04-10',
        'test@abv.bg',
        1,
        (SELECT U.USER_ID
         FROM USERS U
         WHERE U.USERNAME = 'test4'
        ));

--individual with everything of user test01
INSERT INTO INDIVIDUALS
(FIRST_NAME,
 MIDDLE_NAME,
 LAST_NAME,
 CARD_NUMBER,
 BIRTH_DATE,
 PHONE_NUMBER,
 EMAIL,
 IS_ACTIVE,
 USER_ID)
VALUES ('test',
        'test',
        'test',
        '4000001145739170',
        '20-04-10',
        0878152289,
        'test@abv.bg',
        0,
        (SELECT U.USER_ID
         FROM USERS U
         WHERE U.USERNAME = 'test01'
        ));

--individual with empty phone number of user test03
INSERT INTO INDIVIDUALS
(FIRST_NAME,
 MIDDLE_NAME,
 LAST_NAME,
 CARD_NUMBER,
 BIRTH_DATE,
 EMAIL,
 IS_ACTIVE,
 USER_ID)
VALUES ('test',
        'test',
        'test',
        '4001000745739170',
        '20-04-10',
        'test@abv.bg',
        1,
        (SELECT U.USER_ID
         FROM USERS U
         WHERE U.USERNAME = 'test03'
        ));

--individual with everything of user test04
INSERT INTO INDIVIDUALS
(FIRST_NAME,
 MIDDLE_NAME,
 LAST_NAME,
 CARD_NUMBER,
 BIRTH_DATE,
 PHONE_NUMBER,
 EMAIL,
 IS_ACTIVE,
 USER_ID)
VALUES ('test',
        'test',
        'test',
        '4090000045739170',
        '20-04-10',
        0878152289,
        'test@abv.bg',
        0,
        (SELECT U.USER_ID
         FROM USERS U
         WHERE U.USERNAME = 'test04'
        ));


--address with everything of user test
INSERT INTO ADDRESSES
(COUNTRY,
 CITY,
 DISTRICT,
 INDIVIDUAL_ID)
VALUES ('Belgia',
        'Brussels',
        'Brussels',
        (SELECT I.INDIVIDUAL_ID
         FROM INDIVIDUALS I
         WHERE I.USER_ID =
               (SELECT U.USER_ID
                FROM USERS U
                WHERE U.USERNAME = 'test'
               )));

--address with everything of user test01(RENTER)
INSERT INTO ADDRESSES
(COUNTRY,
 CITY,
 DISTRICT,
 INDIVIDUAL_ID)
VALUES ('Belgia',
        'Brussels',
        'Brussels',
        (SELECT I.INDIVIDUAL_ID
         FROM INDIVIDUALS I
         WHERE I.USER_ID =
               (SELECT U.USER_ID
                FROM USERS U
                WHERE U.USERNAME = 'test01'
               )));

--update address of user test
UPDATE ADDRESSES
SET COUNTRY  = 'Serbia',
    CITY     = 'Nish',
    DISTRICT = 'Nish'
WHERE INDIVIDUAL_ID =
      (SELECT I.INDIVIDUAL_ID
       FROM INDIVIDUALS I
       WHERE I.USER_ID = (SELECT U.USER_ID
                          FROM USERS U
                          WHERE U.USERNAME = 'test'
       ));

-- guest house with everything of user test
INSERT INTO GUEST_HOUSES
(GUEST_HOUSE_ID,
 NAME_OF_HOUSE,
 KM_TO_THE_CAPITAL,
 NEAREST_CITY,
 QUADRATURE,
 NUM_OF_FLOORS,
 NUM_OF_RENTED_DAYS,
 PAYMENT_PER_DAY,
 OWNER_ID)
VALUES (900,
        'testHouse',
        50,
        'test',
        200,
        2,
        0,
        90,
        (SELECT U.USER_ID
         FROM USERS U
         WHERE USERNAME = 'test'
        ));

INSERT INTO GUEST_HOUSES
(GUEST_HOUSE_ID,
 NAME_OF_HOUSE,
 KM_TO_THE_CAPITAL,
 NEAREST_CITY,
 QUADRATURE,
 NUM_OF_FLOORS,
 NUM_OF_RENTED_DAYS,
 PAYMENT_PER_DAY,
 OWNER_ID)
VALUES (901,
        'testHouse',
        50,
        'test',
        200,
        2,
        0,
        90,
        (SELECT U.USER_ID
         FROM USERS U
         WHERE USERNAME = 'test'
        ));



-- rent house user01(RENTER)
UPDATE GUEST_HOUSES
SET NUM_OF_RENTED_DAYS = 5,
    RENTER_ID          = (SELECT U.USER_ID
                          FROM USERS U
                          WHERE U.USERNAME = 'test01')
WHERE GUEST_HOUSE_ID = 900;



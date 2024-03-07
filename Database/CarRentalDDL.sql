CREATE DATABASE IF NOT EXISTS CarRental;

create table if not exists CarRental.Car
(
    ID                int auto_increment primary key,
    Car_CategoryID    int         not null,
    Brand             varchar(50) not null,
    Model             varchar(50) not null,
    TypeOfFuel        varchar(20) not null,
    License_Plate     varchar(50) not null,
    Registration_Date date        not null,
    Mileage           int         null,
    constraint car_category_fk foreign key (Car_CategoryID) references carrental.Car_Category (ID)
);

create table if not exists CarRental.Car_Category
(
    ID   int auto_increment primary key,
    Type varchar(50) not null
);

create table if not exists CarRental.Contract
(
    ID              int auto_increment primary key,
    CustomerID      int  not null,
    CarID           int  not null,
    FromDateAndTime date not null,
    ToDateAndTime   date not null,
    MaxKm           int  not null,
    constraint CarID_fk
        foreign key (CarID) references carrental.Car (ID),
    constraint CustomerID_fk
        foreign key (CustomerID) references carrental.Customer (ID)
);

create table if not exists CarRental.Customer
(
    ID                 int auto_increment primary key,
    Name               varchar(50) not null,
    Address            varchar(50) not null,
    Zipcode            int         not null,
    Mobile_phone       int         not null,
    Email              varchar(50) not null,
    License_Number     int         not null,
    License_Issue_Date date        not null,
    constraint zipcode_fk
        foreign key (Zipcode) references carrental.zipcode_city (zipcode)
);

create table if not exists CarRental.zipcode_city
(
    zipcode int         not null primary key,
    city    varchar(50) not null
);


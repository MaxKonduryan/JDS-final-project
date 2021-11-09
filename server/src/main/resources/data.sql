drop table if exists account;
drop table if exists card;
drop table if exists atm;

create table account (
    id          int             primary key,
    balance     decimal(20, 2)  default 0,
    currency    char(3)         default 'RUR'
);

create table card (
    number      varchar(20)     primary key,
    pincode     varchar(99)     default '',
    account     int             not null,
    foreign key (account) references account(id)
);

create table atm (
    name        varchar(20)     primary key,
    code        varchar(99)     default '---',
    role        varchar(99)     default 'ATM'
);

insert into account (
    id,
    balance,
    currency
) values
    (1, 100.25, 'RUR'),
    (2, 550.56, 'USD'),
    (3,   1.97, 'EUR'),
    (4, 600.00, 'RUR'),
    (5,  10.66, 'USD');

insert into card (
    number,
    pincode,
    account
) values
    ('1234', '{bcrypt}$2a$10$tTg/LBxwI8BhQ3jnPIFvkeiDQUN/Wf9/KkTPPG8t1O6MoFp0XMsdm', 1), -- 0404
    ('1235', '{bcrypt}$2a$10$7tueEkC5vihepMfVLVTZxumeZKDOx.w9yU5pkqsOWLvAjc99MSIJG', 2), -- 0505
    ('1236', '{bcrypt}$2a$10$lf6b/X25VOjKLzuVWU/OYeOcvC4Ij19QiNA/ANBZN9jLWT5TW4YSu', 3), -- 0606
    ('1237', '{bcrypt}$2a$10$EAsQ83OqWEH.DsY4HlJeaeU87LUd1chMusPGgTafhRWPPXWvrUAK.', 4), -- 0707
    ('1238', '{bcrypt}$2a$10$F9ooryqd7r/NqokVFbOcq.sp4Gu1e2Vq/4kdZPHobpWkIMf7pEMv6', 5); -- 0808

insert into atm (
    name,
    code,
    role
) values
    ('ATM001', '{bcrypt}$2a$10$v53ZeifN/33d3SgWT0bn1.Nbsu/j6CEKTfgBhxk3xo./ZiU7e1s8q', 'ATM'), -- 100MTA
    ('ATM002', '{bcrypt}$2a$10$bHJ74NMeigDBkZrj51hY3O3h4gJBBcH7aQ6BpGiSQaal7vzDCFjkW', 'ATM'), -- 200MTA
    ('ATM003', '{bcrypt}$2a$10$cihL.GRwT12vp6UKiNGi6O1apAdSWEnQTi6LomnICuMhyS6Gx4ZXK', 'ATM'); -- 300MTA
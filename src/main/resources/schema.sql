DROP TABLE IF EXISTS accounts;
CREATE TABLE accounts (
                          accountId varchar(10),
                          accountName varchar(10),
                          accountPassword varchar(20),
                          accountEmail varchar(20),
                          PRIMARY KEY (accountId)
);
INSERT INTO accounts(accountId, accountName, accountPassword, accountEmail)
VALUES
    ('m1', 'watanabe', '123456', 'watanabe@keio.jp'),
    ('m2', 'tanaka', '123456', 'tanaka@keio.jp'),
    ('m3', 'nakamura', '123456', 'nakamura@keio.jp'),
    ('p1', 'john', '123456', 'john@keio.jp'),
    ('p2', 'annie', '123456', 'annie@keio.jp'),
    ('p3', 'tom', '123456', 'tom@keio.jp');

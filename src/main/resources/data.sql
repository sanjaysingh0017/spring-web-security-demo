insert into users (id, mobile_number, credit_card_number, name, password, username, enabled)
values (1001, 7462865738, 9876897654678976, 'Sanjay Singh', '$2a$12$6qscKhRdmPMHAXvrl8shtuRbgrX83v/OBBK5YLEXUu/yceUE7mVIK','sanjay123', 1);
insert into users (id, mobile_number, credit_card_number, name, password, username, enabled)
values (1002, 8747354768, 7563859352759674, 'Hacker', '$2a$12$1gotKRGCJuptfp3AAp179.Go0nx.ZS7AWrepVF2nwtwrMv6poiUYm','hacker123', 1);

INSERT INTO authorities (username, authority)
  values ('sanjay123', 'ROLE_USER');
INSERT INTO authorities (username, authority)
    values ('hacker123', 'ROLE_USER');
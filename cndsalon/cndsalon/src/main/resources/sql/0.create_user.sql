create user CNDSALON identified by "123";
grant connect, resource to CNDSALON;
conn cndsalon/123;

DROP USER CNDSALON CASCADE;

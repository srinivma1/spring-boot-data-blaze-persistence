# spring-boot-sample-data-jpa-h2
Workaround for pagination in JPARepository especially for OneToOne association using Blaze API. Please refer to the below Hibernate bug on ONeToOne association.

https://hibernate.atlassian.net/browse/HHH-12307


In the Repository class, return type is EntityView class and method name should not overlap with super class i.e.findAll() method will overlap with the method name with super class. You cant override this method as well since the return type is EntityView whereas expected is Entity class. It can use Spring Data keywords like ByIdNotNull etc.

Also, please refer to the main application for JPARepository and EntityView configuration.






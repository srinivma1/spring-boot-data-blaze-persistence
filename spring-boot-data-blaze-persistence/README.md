# spring-boot-sample-data-jpa-h2
I am interested in displaying only bookCategory id, bookCategoryName and from Book collection, only the bookName. So, I created an EntityView for BookCategory and Book entity called BookCategoryView and BookView.

If you look at the IndexController class, there is one method called getBookCategoryOrderById(). In this method, I am expecting three request parameters from UI.

1. Id - Since I am ordering by Book Category Id required for pagination, I am passing the previous page's last bookCategory Id. If it is the first page, then this parameter need not be passed. 

                          Book Category ID        Book Category Name

                                     1                              Category A

                                     2                               Category B

                                     3                               Category C        



In the above example, lets say previous page contained above query results and I am sorting by book category ID, the last ID present is 3. So, 3 will be passed as parameter in this case which will be used as keyset reference for displaying next page.      

                             

2. before - This is required for keyset pagination. So, If I am in the second page, I will pass the last ID present in the second page and set this value to yes and it will display the previous page results. Below is the SQL Query executed if I wanted to go to the previous page.

Hibernate: select bookcatego0_.id as col_0_0_, books1_.name as col_1_0_, bookcatego0_.name as col_2_0_ from book_category bookcatego0_ left outer join book books1_ on bookcatego0_.id=books1_.book_category_id where bookcatego0_.id<? order by bookcatego0_.id ASC nulls last limit ?



3. after - This is required for keyset pagination. If I am in the first page, I will pass the last ID present in the previous page and set this value to yes and it will display the next page results.

Hibernate: select bookcatego0_.id as col_0_0_, books1_.name as col_1_0_, bookcatego0_.name as col_2_0_ from book_category bookcatego0_ left outer join book books1_ on bookcatego0_.id=books1_.book_category_id where bookcatego0_.id>? order by bookcatego0_.id ASC nulls last limit

So, in effect, I can scroll backward/forward with just one single query each time instead of multiple queries fired. Of course, if the results for previous scroll was cached in UI, there is no need to even call the above query. Also, number of results to be displayed in each scroll is mentioned in BookCategoryDAO class. Search for setMaxResults(20) in the DAO class.

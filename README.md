# StockManagementSystem
Spring, gradle, PostgreSQL

## use case
1. user bisa lihat most recent transactions
`query tabel transactions`
2. user bisa lihat untuk tiap barang history kluar masuk barang
`barang kluar: query tabel transactions where EQUAL item_id, brang masuk: query tabel productions where EQUAL prod_id`
3. user bisa lihat history beli customer
`query tabel transactions where EQUAL cust id`

https://dbdiagram.io/d/5e88a5724495b02c3b893fc2
<img src="https://github.com/JoshEvan/StockManagementSystem/blob/master/StockManagementSystem_v2.png?raw=true"/>

nice soruce:
https://www.princeton.edu/~rcurtis/ultradev/ecommdatabase.html
https://www.princeton.edu/~rcurtis/ultradev/images/storediagram.gif



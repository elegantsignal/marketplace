# Marketplace project description

## Cases

### Get list of all user's orders

We need to show all user's orders with bought products and download links for each product. I found several solutions:

- Get all user's orders, then for each order get order item. Obvious that it is not good solution because we have unpredictable number of database queries.

- Use OneToMany relationship. It looks better, but we can suggest that we don't know how many data will be pulled cascaded from DB (other tables depended on `order` may have OneToMany relationship).

- Get all order items with one query and work with them. But we will need to groupe them in some way.

The last solution was chosen. We get all order items then map them to orders. You can found that `OrderItem` have `@OneToMany` relationship to `download`, we admit that because `download` table have only one relationship (with `order_item`).

```sql
SELECT
  order_item.id,
  product.id,
  book.id,
  "order".id,
  download.id
  -- Other data from all tables
FROM
  order_item
  LEFT OUTER JOIN product ON order_item.product_id = product.id
  LEFT OUTER JOIN book ON product.id = book.id
  LEFT OUTER JOIN "order" ON order_item. "order_id" = "order".id
  LEFT OUTER JOIN download ON order_item.id = download.order_item_id
WHERE
  "order"."user_id" = 1;

```

```java
  public List<IOrder> getOrdersByUserId(final Integer userId) {
	final List<IOrderItem> orderItemList = orderItemService.getOderItemsByUserId(userId);

	final Set<IOrder> orderSet = new HashSet<>();
	orderItemList.forEach(orderItem -> {
		final IOrder order = orderItem.getOrder();
		orderSet.add(order);
		order.addOrderItem(orderItem);
	});

	final List<IOrder> orderList = new ArrayList<>(orderSet);
	orderList.stream().sorted(Comparator.comparing(IOrder::getCreated));
	return orderList;
	}
```

We use `Set` to avoid to search proper **order** in list, on the other hand - we need to convert them to sorted list;

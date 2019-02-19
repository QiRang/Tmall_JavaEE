package product;

import java.util.List;

public interface ProductCategoryDao {
	public void add(ProductCategory productCategory);
	public List<ProductCategory> load();
	public List<ProductCategory> load(int startIndex, int pageSize);
	public void delete(int productCetegoryId);
}

package povos;

import java.util.ArrayList;
import java.util.List;

import com.jiaming.entity.Category;

public class CategoryPovo extends Category {

	private List<Category> children = new ArrayList<Category>();

	public CategoryPovo() {
		super();
	}

	public CategoryPovo(List<Category> children) {
		super();
		this.children = children;
	}

	public List<Category> getChildren() {
		return children;
	}

	public void setChildren(List<Category> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "CategoryPovos [children=" + children + "]";
	}
	
}

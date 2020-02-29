package design_pattern.kosta.behavior.iterator;

public class Client {

	public static void main(String[] args) {


		BookShelf bs = new BookShelf(4);

		bs.addBook(new Book("java"));
		bs.addBook(new Book("c"));
		bs.addBook(new Book("C++"));
		bs.addBook(new Book("js"));

		MyIterator myIterator = bs.iterator();
		while (myIterator.hasNext()) {
			Book next = (Book) myIterator.next();
 			System.out.println(next.getName());
		}

	}

}

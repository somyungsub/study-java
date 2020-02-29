package design_pattern.kosta.creation.pool;

public class Client {

	public static void main(String[] args) {

		// TODO

		ObjectPool<StringBuffer> pool = new ObjectPool<>(StringBuffer.class, 2);

		StringBuffer sb1 = pool.getObject();
		StringBuffer sb2 = new StringBuffer();
		System.out.println(pool.invalidate(sb1));
		System.out.println(pool.invalidate(sb2));

		StringBuffer sb3 = pool.getObject();
		StringBuffer sb4 = pool.getObject();

	}

}

package com.hqyg.util;

import com.util.MapUtils;

import java.util.*;

/**
 * 
 * @author lenovo 方法介绍 n : 创建 a : 添加 cv : 判断是否存在 r : 删除 c : 清理 s : 大小 g : 取出 to
 *         : 转化为原型 each : 遍历 filter : 过滤 group : 分组 join : 数组拼装为字符串，调用toString
 *         list : 重写list的值，保持原有序列的顺序 sort : 排序
 * @param <T>
 */

public final class ListUtil<T> {

	// private static final long serialVersionUID = 1L;

	@FunctionalInterface
	public static interface GroupIterable<V, K> {
		K accept(final V v);
	}

	@FunctionalInterface
	public static interface EachIterable<T> {
		void accept(final T t);
	}

	@FunctionalInterface
	public static interface FilterIterable<T> {
		boolean test(final T t);
	}

	@FunctionalInterface
	public static interface ListNewList<T, N> {
		N accept(T t);
	}

	@FunctionalInterface
	public static interface MaxOrMin<T, N extends Comparable<N>> {
		N accept(final T t);
	}

	@FunctionalInterface
	public static interface OrderBy<T, N extends Comparable<N>> {
		N accept(final T t);
	}

	@FunctionalInterface
	public static interface UniqueIterable<T, N> {
		N accept(final T t);
	}

	private final List<T> list = new LinkedList<>();

	private ListUtil() {
	}

	/**
	 * 新建一个空得ListUtls
	 * 
	 * @return
	 */
	public final static <T> ListUtil<T> n() {
		return new ListUtil<T>();
	}

	/**
	 * 新建一个得ListUtls
	 * 
	 * @return
	 */
	public final static <T> ListUtil<T> n(final Iterable<T> collection) {
		return ListUtil.<T>n().a(collection);
	}

	/**
	 * 新建一个得ListUtls
	 * 
	 * @return
	 */
	public final static <T> ListUtil<T> n(@SuppressWarnings("unchecked") final T... ts) {
		return ListUtil.<T>n().a(ts);
	}

	/**
	 * 向ListUtls追加一个
	 * 
	 * @return
	 */
	public final ListUtil<T> a(final T t) {
		if (t != null)
			this.list.add(t);
		return this;
	}

	/**
	 * 向ListUtls追加一组
	 * 
	 * @return
	 */
	public final ListUtil<T> a(@SuppressWarnings("unchecked") final T... ts) {
		if (ts != null)
			for (T t : ts) {
				this.list.add(t);
			}
		return this;
	}

	/**
	 * 向ListUtls追加一组
	 * 
	 * @return
	 */
	public final ListUtil<T> a(final Iterable<T> ts) {
		if (ts != null) {
			ts.forEach(this.list::add);
		}
		return this;
	}

	/**
	 * 判断某个值是否在ListUtls中存在
	 * 
	 * @return
	 */
	public final boolean cv(final T t) {
		return this.list.contains(t);
	}

	/**
	 * 查询ListUtls大小
	 * 
	 * @return
	 */
	public final int s() {
		return this.list.size();
	}

	/**
	 * 从ListUtls获取一个
	 * 
	 * @return
	 */
	public final T g(final int index) {
		return this.list.get(index);
	}

	/**
	 * 删除一组
	 * 
	 * @param indexs
	 * @return
	 */
	public final ListUtil<T> r(final int... indexs) {
		for (int index : indexs) {
			this.list.remove(index);
		}
		return this;
	}

	/**
	 * 删除一个
	 * 
	 * @param t
	 * @return
	 */
	public final ListUtil<T> r(final T t) {
		this.list.remove(t);
		return this;
	}

	/**
	 * 删除一组
	 * 
	 * @param ts
	 * @return
	 */
	public final ListUtil<T> r(@SuppressWarnings("unchecked") final T... ts) {
		for (T t : ts) {
			this.list.remove(t);
		}
		return this;
	}

	/**
	 * 清理list
	 * 
	 * @return
	 */
	public final ListUtil<T> c() {
		this.list.clear();
		return this;
	}

	/**
	 * 转化为列表原型
	 * 
	 * @return
	 */
	public final List<T> to() {
		return this.list;
	}

	/**
	 * 遍历
	 * 
	 * @param eachIterable
	 * @return
	 */
	public final ListUtil<T> each(final EachIterable<? super T> eachIterable) {
		Iterator<T> it = this.list.iterator();
		while (it.hasNext()) {
			T t = (T) it.next();
			eachIterable.accept(t);
		}
		return this;
	}

	/**
	 * 按指定方案去重
	 * 
	 * @param uniqueIterable
	 * @return
	 */
	public final <N> ListUtil<T> unique(UniqueIterable<T, N> uniqueIterable) {
		Map<N, T> map = new HashMap<>(this.s());
		this.each(t -> {
			N key = uniqueIterable.accept(t);
			if (!map.containsKey(key))
				map.put(key, t);
		});
		return n(map.values());
	}

	/**
	 * 去重
	 * 
	 * @return
	 */
	public final <N> ListUtil<T> unique() {
		ListUtil<T> lu = n();
		this.each(t -> {
			if (!lu.cv(t))
				lu.a(t);
		});
		return lu;
	}

	/**
	 * 按指定方式正排序
	 * 
	 * @param orderby
	 * @return
	 */
	public final <N extends Comparable<N>> ListUtil<T> order(final OrderBy<T, N> orderby) {
		this.list.sort((t1, t2) -> orderby.accept(t1).compareTo(orderby.accept(t2)));
		return this;
	}

	/**
	 * 按指定方式到排序
	 * 
	 * @param orderby
	 * @return
	 */
	public final <N extends Comparable<N>> ListUtil<T> orderDesc(final OrderBy<T, N> orderby) {
		this.list.sort((t1, t2) -> -1 * orderby.accept(t1).compareTo(orderby.accept(t2)));
		return this;
	}

	/**
	 * 获取最小得
	 * 
	 * @param maxOrMin
	 * @return
	 */
	public final <N extends Comparable<N>> T min(final MaxOrMin<? super T, N> maxOrMin) {
		return m(maxOrMin, -1);
	}

	/**
	 * 获取最大得
	 * 
	 * @param maxOrMin
	 * @return
	 */
	public final <N extends Comparable<N>> T max(final MaxOrMin<? super T, N> maxOrMin) {
		return m(maxOrMin, 1);
	}

	private final <N extends Comparable<N>> T m(final MaxOrMin<? super T, N> maxOrMin, int status) {
		if (isEmpty(this.list))
			return null;
		T mcv = null;
		N mom = null;
		for (T t : list) {
			N cv = maxOrMin.accept(t);
			if (cv == null)
				continue;
			if (mcv == null) {
				mcv = t;
				mom = cv;
				continue;
			}
			if (cv.compareTo(mom) * status > 0) {
				mcv = t;
				mom = cv;
			}
		}
		return mcv;
	}

	/**
	 * 操作返回false会被删除，返回true会留下
	 * 
	 * @param filter
	 * @return
	 */
	public final ListUtil<T> filter(final FilterIterable<? super T> filter) {
		this.list.removeIf(t -> !filter.test(t));
		return this;
	}

	/**
	 * 转化列表里边每一个值
	 * 
	 * @param lnl
	 * @return
	 */
	public final <N> ListUtil<N> list(final ListNewList<? super T, N> lnl) {
		ListUtil<N> lu = ListUtil.n();
		this.each(t -> {
			N n = lnl.accept(t);
			if (n != null)
				lu.a(n);
		});
		return lu;
	}

	/**
	 * 按指定方式分组
	 * 
	 * @param groupIterable
	 * @return
	 */
	public final <K> MapUtils<K, List<T>> group(final GroupIterable<? super T, K> groupIterable) {
		MapUtils<K, List<T>> relust = MapUtils.n();
		this.each(t -> {
			if (t == null)
				return;
			K key = groupIterable.accept(t);
			if (key == null)
				return;
			List<T> it = relust.ck(key) ? relust.g(key) : new LinkedList<>();
			it.add(t);
			relust.a(key, it);
		});
		return relust;
	}

	/**
	 * 链接每一个对象
	 * 
	 * @return
	 */
	public final String join() {
		return this.join(null);
	}

	/**
	 * 按指定方式链接每一个对象
	 * 
	 * @param seg
	 * @return
	 */
	public final String join(final String seg) {
		if (list.size() == 0)
			return "";
		if (list.size() == 1)
			return String.valueOf(list.get(0));
		StringBuffer ret = new StringBuffer();
		ret.append(list.get(0));
		for (int i = 1; i < list.size(); i++) {
			T t = list.get(i);
			if (seg != null)
				ret.append(seg);
			ret.append(t);
		}
		return ret.toString();
	}

	public boolean isNotEmpty() {
		return !this.list.isEmpty();
	}

	public boolean isEmpty() {
		return this.list.isEmpty();
	}

	/**
	 * 判断列表都不是空
	 * 
	 * @param collections
	 * @return
	 */
	public final static boolean isNotEmpty(final Collection<?>... collections) {
		for (Collection<?> collection : collections) {
			if (collection == null || collection.isEmpty())
				return false;
		}
		return true;
	}

	/**
	 * 判断列表是否有一个为空
	 * 
	 * @param args
	 * @return
	 */
	public final static boolean isEmpty(final Collection<?>... args) {
		return !isNotEmpty(args);
	}
}
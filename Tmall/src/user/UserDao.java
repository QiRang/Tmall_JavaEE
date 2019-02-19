package user;

import java.util.List;

import utils.Page;


interface UserDao {
	//ÔöÉ¾¸Ä²é
	public void add(User user);
	public void delete(int userId);
	public void update(User user);
	public User load(int userId);
	public User load(String userName);
	public void login(User user); 
	public List<User> load();
	public List<User> load(int startIndex, int pageSize);
	public Page<User> findAllUserWithPage(int pageNum, int pageSize);
	public int count();

}

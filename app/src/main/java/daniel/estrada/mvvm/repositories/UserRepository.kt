package daniel.estrada.mvvm.repositories

import daniel.estrada.mvvm.models.User
import daniel.estrada.mvvm.daos.UserDao

class UserRepository(private val userDao: UserDao) {

    suspend fun insert(user: User) = userDao.insert(user)

    suspend fun update(user: User) = userDao.update(user)

    suspend fun delete(user: User) = userDao.delete(user)

    fun find(args: String) = userDao.find(args)

    fun getAll() = userDao.getAll()

    //suspend fun findById(id: Int) = userDao.findById(id)
}
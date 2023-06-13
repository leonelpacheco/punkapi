package com.leonel.intercamlp.repository




import com.leonel.intercamlp.database.dao.userDao
import com.leonel.intercamlp.database.entities.userEntity
import com.leonel.intercamlp.model.add
import com.leonel.intercamlp.model.user
import javax.inject.Inject

class userRepository @Inject constructor(private val userDao: userDao) {

    suspend fun getAllUsesrsFromDatabase():List<user>{
        val response: List<userEntity> = userDao.getAllUsers()
        return response.map { it.add() }
    }
    //**********************
    suspend fun insertUser(user:userEntity){
        userDao.insertAll(user)
    }

    suspend fun clearUsers(){
        userDao.deleteAllUsers()
    }

    suspend fun getConsultUsesrsFromDatabase(user: user):List<user>{
        val response: List<userEntity> = userDao.getConsultUser(user.user,user.password)
        return response.map { it.add() }
    }


}
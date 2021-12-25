package Nurbol.Repositories;

import Nurbol.Entities.Userdetail;

import java.util.List;

public interface UserdetailRepository {
    List<Userdetail> findAll();
    Userdetail findById(Long id);
    Userdetail deleteById(int id);

}

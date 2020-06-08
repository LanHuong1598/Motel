package com.example.intergration.motel.controller;

import com.example.intergration.motel.beans.Service;
import com.example.intergration.motel.beans.User;
import com.example.intergration.motel.beans.UserService;
import com.example.intergration.motel.exception.UserNotFoundException;
import com.example.intergration.motel.repository.ServiceRepository;
import com.example.intergration.motel.repository.UserRepository;
import com.example.intergration.motel.repository.UserServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserServiceRepository userServiceRepository;

    @Autowired
    ServiceRepository serviceRepository;

    private boolean isAdmin( int id ){
        Optional<User> user = userRepository.findByIduser(id);

        return user.map(value -> value.getIdgroup().equals(String.valueOf(1))).orElse(false);
    }

    //Hiển thị tất cả người dùng
    @GetMapping("/{admin_id}/users")
    public ResponseEntity<List<User>> getAllUser(
            @PathVariable(name = "admin_id") int id
    ){
        if ( !isAdmin(id) ){
            return new ResponseEntity<>( HttpStatus.UNAUTHORIZED);
        }
        return ResponseEntity.ok(
                userRepository.findAll()
        );
    }

    //Tìm kiếm người dùng theo id
    @GetMapping("/{admin_id}/users/id/{id}")
    public ResponseEntity<User> searchUserById(
            @PathVariable(name = "id") int id,
            @PathVariable(name = "admin_id")int admin_id
    ){
        if (!isAdmin(admin_id)){
            return new ResponseEntity<>( HttpStatus.UNAUTHORIZED);
        }
        Optional<User> user = userRepository.findByIduser(id);

        if ( user.isPresent() ){
            return ResponseEntity.ok( user.get() );
        }
        else{
            throw new UserNotFoundException("User not found");
        }
    }

    //Tìm kiếm người dùng theo acc
    @GetMapping("/{admin_id}/users/username/{username}")
    public ResponseEntity<List<User>> searchUserByUsername(
            @PathVariable(name = "admin_id") int admin_id,
            @PathVariable(name = "username") String username
    ){
        if ( isAdmin(admin_id)){
            return ResponseEntity.ok(
                    userRepository.findAllByAcc(username)
            );
        }
        else{
            return new ResponseEntity<>( HttpStatus.UNAUTHORIZED);
        }
    }

    //Chỉnh sửa thong tin người dùng
    @PutMapping("/{admin_id}/users/{userid}")
    public ResponseEntity<User> editUser(
            @PathVariable(name = "admin_id") int admin_id,
            @PathVariable(name = "userid") int userid,
            @RequestBody User user
    ){
        if (!isAdmin(admin_id)){
            return new ResponseEntity<>( HttpStatus.UNAUTHORIZED);
        }
        else{
            Optional<User> _user = userRepository.findByIduser(userid);
            if ( _user.isPresent() ){
                _user.get().setAcc( user.getAcc() );
                _user.get().setPassword( user.getPassword());
                _user.get().setIdgroup( user.getIdgroup());
                return new ResponseEntity<>(
                        userRepository.save( _user.get() ),
                        HttpStatus.OK
                );
            }
            else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Xóa người dùng
    @DeleteMapping("/{admin_id}/users/{userid}")
    public ResponseEntity<User> deleteUser(
            @PathVariable(name = "admin_id") int admin_id,
            @PathVariable(name = "userid") int userid
    ){
        if (!isAdmin(admin_id)){
            return new ResponseEntity<>( HttpStatus.UNAUTHORIZED);
        }
        else{
            Optional<User> _user = userRepository.findByIduser(userid);
            if( !_user.isPresent() ){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }else{
                try{
                    userRepository.delete( _user.get() );
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
                catch (Exception e ){
                    return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
                }
            }

        }
    }

    //Hiển thị tất cả người dùng
    @GetMapping("/{admin_id}/services")
    public ResponseEntity<List<Service>> getAllService(
            @PathVariable(name = "admin_id") int id
    ){
        if ( !isAdmin(id) ){
            return new ResponseEntity<>( HttpStatus.UNAUTHORIZED);
        }
        return ResponseEntity.ok(
                serviceRepository.findAll()
        );
    }

    //Chỉnh sửa thong tin
    @PutMapping("/{admin_id}/services/{serviceID}")
    public ResponseEntity<Service> editServiceByID(
            @PathVariable(name = "admin_id") int admin_id,
            @PathVariable(name = "serviceID") int serviceID,
            @RequestBody Service service
    ){
        if (!isAdmin(admin_id)){
            return new ResponseEntity<>( HttpStatus.UNAUTHORIZED);
        }
        else{
            Optional<Service> service1 = serviceRepository.findByIdservice(serviceID);
            if ( service1.isPresent() ){
                service1.get().setName( service.getName() );
                service1.get().setMoney( service.getMoney());
                Calendar c1 = Calendar.getInstance() ;
                java.util.Date date = c1.getTime();
                c1.roll(Calendar.DATE, 30);
                service.setTimestart(service.getTimestart());
                service.setTimeend(service.getTimeend());
                return new ResponseEntity<>(
                        serviceRepository.save( service1.get() ),
                        HttpStatus.OK
                );
            }
            else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    //Chỉnh sửa thong tin
    @PostMapping("/{admin_id}/services/")
    public ResponseEntity<Service> CreateService(
            @PathVariable(name = "admin_id") int admin_id,
            @RequestBody Service service
    ) {
        if (!isAdmin(admin_id)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            return new ResponseEntity<>(
                    serviceRepository.save(service),
                    HttpStatus.OK
            );
        }
    }
    //Chỉnh sửa thong tin người dùng
    @PutMapping("/{admin_id}/service/{serviceId}")
    public ResponseEntity<Service> editService(
            @PathVariable(name = "admin_id") int admin_id,
            @PathVariable(name = "serviceId") int serviceid,
            @RequestBody Service service
    ){
        if (!isAdmin(admin_id)){
            return new ResponseEntity<>( HttpStatus.UNAUTHORIZED);
        }
        else{
            Optional<Service> service1 = serviceRepository.findById(serviceid);
            if (service1.isPresent() ){
                service1.get().setIdservice( service.getIdservice() );
                service1.get().setName(service.getName());
                service1.get().setTimestart( service.getTimestart());

                service1.get().setTimeend( service.getTimeend());
                return new ResponseEntity<>(
                        serviceRepository.save( service1.get() ),
                        HttpStatus.OK
                );
            }
            else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Xóa người dùng
    @DeleteMapping("/{admin_id}/service/{serviceid}")
    public ResponseEntity<User> deleteService(
            @PathVariable(name = "admin_id") int admin_id,
            @PathVariable(name = "serviceid") int serviceid
    ){
        if (!isAdmin(admin_id)){
            return new ResponseEntity<>( HttpStatus.UNAUTHORIZED);
        }
        else{
            Optional<Service> service = serviceRepository.findById(serviceid);
            if( !service.isPresent() ){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }else{
                try{
                    serviceRepository.delete( service.get() );
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
                catch (Exception e ){
                    return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
                }
            }

        }
    }




}

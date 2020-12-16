package api.users.ui.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.env.Enviroment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ResponseEntity;

import com.photoapp.api.users.ui.model.createUserRequestModel;
import com.photoapp.api.users.service.*;
import com.photoapp.api.users.shared.*;
import api.users.ui.model.CreateUserResponseModel;

@RestController
// esta linea, asigna una ubicacion de ejecucion llamada /users
@RequestMapping("/users")
public class UsersController {

    // declaro las variables env y usersService arriba, agregandole el Autowired
    @Autowired
    private Enviroment env;

    @Autowired
    UsersService usersService;

    // verificacion de puerto, para saber en que puerto estamos trabajando, localhost:8080/stauts/check
    @GetMapping("/status/check")
    public String status()
    {
        return "Working on port " + env.getProperty("local.server.port");
    }

    // consumes y produces, se utilizan para aceptar el ingreso/salida de datos en tipo json y xml
    @PostMapping(
                consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
                produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
                )
    // aca se utiliza @Valid ya que CreateUserRequestModel utiliza validaciones como @NotNull o @Email
    public ResponseEntity<CreateUserResponseModel> createUser(@Valid @RequestBody CreateUserRequestModel userDetails)
    {        
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserDto userDto = modelMapper.map(userDetails, UserDto.class);

        // creo el usuario
        UserDto createdUser = usersService.createUser(userDto);

        CreateUserResponseModel returnValue = model.Mapper.map(createdUser, CreateUserResponseModel.class);

        // le envio como mensaje HTTP que se creó correctamente
        return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
    }
}
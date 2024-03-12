package rs.ac.uns.ftn.springsecurityexample.mapper;

import rs.ac.uns.ftn.springsecurityexample.dto.AppointmentDTO;
import rs.ac.uns.ftn.springsecurityexample.model.Appointment;

import java.util.ArrayList;
import java.util.List;

public class AppointmentMapper {

    public static Appointment toDomain(AppointmentDTO dto) {
        Appointment appointment = new Appointment();
        appointment.setId(dto.getId());
        appointment.setDate(dto.getDate());
        appointment.setTime(dto.getTime());
        appointment.setStatus(dto.getStatus());
        appointment.setType(dto.getType());
        return appointment;
    }
    public static AppointmentDTO toDTO(Appointment appointment) {
        AppointmentDTO dto = new AppointmentDTO();
        dto.setId(appointment.getId());
        dto.setDate(appointment.getDate());
        dto.setTime(appointment.getTime());
        dto.setDoctorId(appointment.getDoctor().getId());
        if(appointment.getUser() != null){
            dto.setUserId(appointment.getUser().getId());
        }
        dto.setClinicId(appointment.getClinic().getId());
        if(appointment.getRoom() != null){
            dto.setRoomId(appointment.getRoom().getId());
        }
        dto.setStatus(appointment.getStatus());
        return dto;
    }

    public static List<AppointmentDTO> toDTOList(List<Appointment> appointments) {
        List<AppointmentDTO> dtos = new ArrayList<AppointmentDTO>();
        for(Appointment appointment : appointments){
            dtos.add(toDTO(appointment));
        }

        return dtos;
    }
}

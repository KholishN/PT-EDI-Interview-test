package com.pt.edi.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pt.edi.dto.DtoResponse;
import com.pt.edi.entity.UserEntity;
import com.pt.edi.repository.UserRepository;
import com.pt.edi.service.UserService;

import jakarta.transaction.Transactional;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public DtoResponse<UserEntity> getDataUser(String userId) {
		List<UserEntity> data = new ArrayList<>();
		String message;

		try {
			if (userId.equalsIgnoreCase("all")) {
				data = userRepository.findAll();
				message = data.isEmpty() ? "Data Tidak Ditemukan" : "Data Ditemukan";
			} else {
				Optional<UserEntity> userOptional = userRepository.findById(Integer.parseInt(userId));
				userOptional.ifPresent(data::add);
				message = userOptional.isPresent() ? "Data Ditemukan" : "Data Tidak Ditemukan";
			}
			return new DtoResponse<>("Sukses", data, Map.of("Get Data", message));
		} catch (NumberFormatException e) {
			return new DtoResponse<>("Gagal", data, Map.of("Get Data", "Format UserID Salah"));
		} catch (Exception e) {
			return new DtoResponse<>("Gagal", data, Map.of("Get Data", "Terjadi Kesalahan Pada Server"));
		}
	}

	@Override
	public DtoResponse<UserEntity> setDataUser(UserEntity param) {
		try {
			Optional<UserEntity> existingUserOptional = userRepository.findById(param.getUserid());
			String saveMessage;

			if (existingUserOptional.isPresent()) {
				UserEntity existingUser = existingUserOptional.get();
				existingUser.setNamalengkap(param.getNamalengkap());
				existingUser.setUsername(param.getUsername());
				existingUser.setPassword(param.getPassword());
				existingUser.setStatus(param.getStatus());
				userRepository.save(existingUser);
				saveMessage = "Data Berhasil Diupdate";
			} else {
				userRepository.save(param);
				saveMessage = "Data Berhasil Disimpan";
			}

			return new DtoResponse<>("Sukses", List.of(), Map.of("Save Data", saveMessage));
		} catch (NumberFormatException e) {
			return new DtoResponse<>("Gagal", List.of(), Map.of("Save Data", "Format UserID Salah"));
		} catch (Exception e) {
			return new DtoResponse<>("Gagal", List.of(), Map.of("Save Data", "Terjadi Kesalahan Pada Server"));
		}
	}

	@Override
	public DtoResponse<UserEntity> delDataUser(String userId) {
		try {
			Integer id = Integer.parseInt(userId);
			Optional<UserEntity> existingUser = userRepository.findById(id);
			String deleteMessage;

			if (existingUser.isPresent()) {
				userRepository.deleteById(id);
				deleteMessage = "Data Berhasil Dihapus";
			} else {
				deleteMessage = "Data Tidak Ditemukan";
			}

			return new DtoResponse<>("Sukses", List.of(), Map.of("Delete Data", deleteMessage));
		} catch (NumberFormatException e) {
			return new DtoResponse<>("Gagal", List.of(), Map.of("Delete Data", "Format UserID Salah"));
		} catch (Exception e) {
			return new DtoResponse<>("Gagal", List.of(), Map.of("Delete Data", "Terjadi Kesalahan Pada Server"));
		}
	}

}

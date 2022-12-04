package com.reagente.manager.manager.service;

import com.reagente.manager.manager.entity.Attachment;
import com.reagente.manager.manager.entity.Professor;
import com.reagente.manager.manager.entity.Projeto;
import com.reagente.manager.manager.repository.AttachmentRepository;
import com.reagente.manager.manager.repository.ProjetoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AttachmentServiceImpl implements AttachmentService{

    private AttachmentRepository attachmentRepository;
    private ProjetoRepository projetoRepository;

    public AttachmentServiceImpl(AttachmentRepository attachmentRepository) {
        this.attachmentRepository = attachmentRepository;
    }

    @Override
    public Attachment saveAttachment(MultipartFile file) throws Exception {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (fileName.contains("..")) {
                throw new Exception("Nome do arquivo vazio" + fileName);
            }


            Attachment attachment = new Attachment(fileName, file.getContentType(), file.getBytes());
            return attachmentRepository.save(attachment);
        } catch (Exception e) {
            throw new Exception("Não foi possivel salvar " + fileName);
        }
    }

    @Override
    public Attachment getAttachment(String fileId) throws Exception {
        return attachmentRepository.findById(fileId).orElseThrow(() -> new Exception("Arquivo não disponivel para a ID " + fileId));
    }
}

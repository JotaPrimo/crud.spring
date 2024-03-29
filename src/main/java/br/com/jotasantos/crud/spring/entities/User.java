package br.com.jotasantos.crud.spring.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "tb_users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "Name is a required field")
    @Size(min = 5, max = 255, message = "The name field must contain between 5 and 255 characters")
    private String name;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Username is a required field")
    @Size(min = 5, max = 255, message = "The username field must contain between 5 and 255 characters")
    private String username;

    @Column(nullable = false)
    @NotBlank(message = "Password is a required field")
    @Size(min = 5, max = 255, message = "The password field must contain between 5 and 255 characters")
    private String password;

    @CPF
    @Column(nullable = false)
    @NotBlank(message = "CPF is a required field")
    @Size(min = 14, max = 14, message = "The CPF field must contain 11 characters")
    private String cpf;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "The data nascimento is a required field")
    @Past
    @Column(name = "data_nascimento")
    private Date dataNascimento;

    @Column(columnDefinition = "BOOLEAN DEFAULT true")
    private boolean ativo = true;

    @Column
    @NotBlank(message = "The field telefone is required")
    @Size(min = 14, max = 14, message = "The field telefone must have 11 characters")
    private String telefone;

    @Column(name = "created_at")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "deleted_at", columnDefinition = "TIMESTAMP DEFAULT NULL")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime deleteddAt = null;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String dataNascimentoFormatada() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        return dateFormat.format(getDataNascimento());
    }

    public LocalDateTime getDeleteddAt() {
        return deleteddAt;
    }

    public void setDeleteddAt(LocalDateTime deleteddAt) {
        this.deleteddAt = deleteddAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", cpf='" + cpf + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", ativo=" + ativo +
                ", telefone='" + telefone + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}

package spring.kotlin.student

import com.fasterxml.jackson.annotation.JsonIgnore
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import lombok.Data
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.util.*
import javax.persistence.*
import kotlin.collections.HashSet

@Entity
@Table(name = "bean__student")
@Serializable
@Data
data class Student (
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id : Long?,

        @Column(name = "first_name", columnDefinition = "varchar(255)") val firstName : String,

        @Column(name = "last_name", columnDefinition = "varchar(255)") val lastName : String,

        @Column(name = "phone", columnDefinition = "varchar(255)") val phone : String
) {
    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.PERSIST,CascadeType.REMOVE]) @JoinColumn(name = "id_teacher") @JsonIgnore lateinit var teacher: Teacher

    @ManyToMany(fetch = FetchType.LAZY, cascade = [CascadeType.PERSIST,CascadeType.REMOVE], mappedBy = "student") @JsonIgnore val course : MutableSet<Course> = HashSet()

    @Column(name = "create_date", updatable = false) @field:CreationTimestamp @Temporal(TemporalType.TIMESTAMP) @Contextual val createdDate: Date = Date()

    @Column(name = "update_date") @field:UpdateTimestamp @Temporal(TemporalType.TIMESTAMP) @Contextual val updatedDate : Date = Date()
}

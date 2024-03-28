package kr.zziririt.zziririt.api.board.service

import kr.zziririt.zziririt.api.board.dto.request.UpdateCategoryNameRequest
import kr.zziririt.zziririt.domain.board.model.CategoryEntity
import kr.zziririt.zziririt.domain.board.repository.CategoryRepository
import kr.zziririt.zziririt.global.exception.ErrorCode
import kr.zziririt.zziririt.global.exception.ModelNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CategoryService (
    private val categoryRepository: CategoryRepository
){
    @Transactional
    fun getCategoryById(categoryId: Long) : CategoryEntity {
        return categoryRepository.findByIdOrNull(categoryId)
            ?: throw ModelNotFoundException(ErrorCode.MODEL_NOT_FOUND)
    }

    fun getAllCategories(): List<CategoryEntity> {
        return categoryRepository.findAll()
    }

    @Transactional
    fun updateCategoryById(categoryId: Long, request: UpdateCategoryNameRequest) {
        val category = categoryRepository.findByIdOrNull(categoryId)
            ?: throw ModelNotFoundException(ErrorCode.MODEL_NOT_FOUND)

        category.updateCategoryName(request.newUpdateCategoryName)

        categoryRepository.save(category)
    }
}
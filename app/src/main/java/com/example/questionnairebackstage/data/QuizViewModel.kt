package com.example.questionnairebackstage.data

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class QuizViewModel : ViewModel() {
    data class QuizViewState(
        val isDatabase: Boolean = false,
        val name: String = "",
        val json: String = ""
    )

    var mutableJsonContentFlow = MutableStateFlow(
        "[\n                                                                                                        {\n                                                                                                            \"type\": \"com.example.wintercamp.questionnaire.SingleChoice\",\n                                                                                                            \"headline\": \"How would you rate this app?\",\n                                                                                                            \"options\": [\n                                                                                                                \"1\",\n                                                                                                                \"2\",\n                                                                                                                \"3\",\n                                                                                                                \"4\",\n                                                                                                                \"5\",\n                                                                                                                \"6\",\n                                                                                                                \"7\",\n                                                                                                                \"8\",\n                                                                                                                \"9\",\n                                                                                                                \"10\"\n                                                                                                            ]\n                                                                                                        },\n                                                                                                        {\n                                                                                                            \"type\": \"com.example.wintercamp.questionnaire.MultipleChoice\",\n                                                                                                            \"headline\": \"How did you learn about the app?\",\n                                                                                                            \"options\": [\n                                                                                                                \"From friends\",\n                                                                                                                \"From the developer\",\n                                                                                                                \"From the Internet\",\n                                                                                                                \"From the Github\",\n                                                                                                                \"From relatives\",\n                                                                                                                \"By myself\",\n                                                                                                                \"From the propaganda\"\n                                                                                                            ]\n                                                                                                        },\n                                                                                                        {\n                                                                                                            \"type\": \"com.example.wintercamp.questionnaire.BlankFill\",\n                                                                                                            \"headline\": \"Please briefly talk about your advice or comment on the app.\"\n                                                                                                        }\n                                                                                                    ]"
    )

    val mutableStateFlow = MutableStateFlow(QuizViewState())
    val stateFlow = mutableStateFlow.asStateFlow()

    fun intoDatabaseState() {
        mutableStateFlow.update { it.copy(isDatabase = true) }
    }

    fun intoImportState() {
        mutableStateFlow.update { it.copy(isDatabase = false) }
    }

    fun jsonUpdate(json: String) {
        mutableJsonContentFlow.update { json }
    }

    fun resultChange(name: String,json: String){
        mutableStateFlow.update { it.copy(name = name, json = json) }
    }
}
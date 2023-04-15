package com.example.questionnairebackstage.ui.screen


import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.contentValuesOf
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.questionnaire.BottomButton
import com.example.questionnairebackstage.App
import com.example.questionnairebackstage.R
import com.example.questionnairebackstage.data.QuizViewModel
import com.example.questionnairebackstage.ui.component.CustomText
import com.example.questionnairebackstage.ui.theme.QuestionnaireBackstageTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImportScreen(
    onNavigateToPrevious: () -> Unit = {},
    onNavigateToNext: () -> Unit = {},
    quizViewModel: QuizViewModel = viewModel()
) {
    val json by quizViewModel.mutableJsonContentFlow.collectAsState()
    val context = LocalContext.current
    BackHandler {
        onNavigateToPrevious()
    }
    QuestionnaireBackstageTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column {
                CustomText(
                    text = stringResource(R.string.please_import_questionnaire_topic_json),
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(top = 35.dp, start = 15.dp)
                )
                OutlinedTextField(
                    value = json,
                    onValueChange = { quizViewModel.jsonUpdate(it) },
                    textStyle = MaterialTheme.typography.titleMedium,
                    label = { CustomText(stringResource(R.string.questionnaire_json)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(all = 15.dp)
                )
                BottomButton(onNavigateToPrevious) {
                    try {
                        val uri =
                            Uri.parse("content://com.example.wintercamp.questionnaire.provider/json")
                        val values = contentValuesOf("json" to json)
                        App.context.contentResolver.insert(uri, values)
                        onNavigateToNext()
                    } catch (e: Exception) {
                        Toast.makeText(context, "App not launch", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun ImportScreenPreview(
) {
    ImportScreen()
}

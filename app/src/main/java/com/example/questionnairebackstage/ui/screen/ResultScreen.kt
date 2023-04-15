package com.example.questionnairebackstage.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.questionnairebackstage.R
import com.example.questionnairebackstage.data.QuizViewModel
import com.example.questionnairebackstage.ui.component.CustomText
import com.example.questionnairebackstage.ui.theme.QuestionnaireBackstageTheme

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun ResultScreen(
    quizViewModel: QuizViewModel = viewModel(),
    onNavigateToDatabaseScreen: () -> Unit = {}
) {
    val viewState by quizViewModel.stateFlow.collectAsState()
    QuestionnaireBackstageTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column {
                OutlinedTextField(
                    value = viewState.name,
                    onValueChange = { },
                    textStyle = MaterialTheme.typography.titleMedium,
                    label = { CustomText(stringResource(R.string.name)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.13f)
                        .padding(horizontal = 15.dp)
                        .padding(top = 20.dp)
                )
                OutlinedTextField(
                    value = viewState.json,
                    onValueChange = { },
                    textStyle = MaterialTheme.typography.titleMedium,
                    label = { CustomText(stringResource(R.string.questionnaire_json)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(all = 15.dp)
                )
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .padding(bottom = 15.dp),
                    onClick = { onNavigateToDatabaseScreen() }) {
                    CustomText(text = "Back To Database")
                }
            }
        }
    }
}
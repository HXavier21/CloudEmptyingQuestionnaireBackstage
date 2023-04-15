package com.example.questionnairebackstage.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.questionnairebackstage.R
import com.example.questionnairebackstage.data.QuizViewModel
import com.example.questionnairebackstage.ui.component.CustomText
import com.example.questionnairebackstage.ui.theme.QuestionnaireBackstageTheme

@Composable
@Preview
fun FinishScreen(
    quizViewModel: QuizViewModel = viewModel(),
    onNavigateToReturn:()->Unit = {}
) {
    val viewState by quizViewModel.stateFlow.collectAsState()
    QuestionnaireBackstageTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                CustomText(
                    text = stringResource(R.string.over),
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier.padding(top = 275.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                Button(
                    onClick = { onNavigateToReturn() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 30.dp)
                ) {
                    CustomText(
                        text = when (viewState.isDatabase) {
                            false -> stringResource(R.string.back_to_home)
                            true -> stringResource(R.string.back_to_database)
                        },
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }
    }
}
package com.example.getar.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.getar.data.model.Gempa
import com.example.getar.ui.GempaViewModel
import com.example.getar.ui.UiState
import com.example.getar.ui.theme.CoralPinkDark
import com.example.getar.ui.theme.CoralPinkLight
import com.example.getar.ui.theme.DeepTealDark
import com.example.getar.ui.theme.DeepTealLight
import com.example.getar.ui.theme.InkText
import com.example.getar.util.magnitudeColor
import com.example.getar.util.orDash
import com.example.getar.util.toMagnitudeDouble

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    gempaIndex: Int,
    viewModel: GempaViewModel,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()
    val isDark = isSystemInDarkTheme()
    val gempa: Gempa? = (uiState as? UiState.Success)?.data?.getOrNull(gempaIndex)

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Detail Gempa",
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBackClick,
                        modifier = Modifier
                            .padding(8.dp)
                            .background(
                                color = MaterialTheme.colorScheme.surface,
                                shape = CircleShape
                            )
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Kembali ke Beranda",
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { innerPadding ->
        if (gempa == null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Data gempa tidak ditemukan",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        } else {
            val magValue = gempa.magnitude.toMagnitudeDouble()
            val heroBgColor = magValue.magnitudeColor(isDark = isDark)
            val heroTextColor = if (magValue in 5.0..5.99 && !isDark) InkText else Color.White

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(28.dp))
                        .background(heroBgColor)
                        .padding(24.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Box(
                            modifier = Modifier
                                .background(
                                    color = if (isDark) Color(0xFF2C2F2B) else Color.White,
                                    shape = RoundedCornerShape(9999.dp)
                                )
                                .padding(horizontal = 14.dp, vertical = 6.dp)
                        ) {
                            Text(
                                text = "${gempa.tanggal.orDash()} • ${gempa.jam.orDash()}",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold
                                ),
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = gempa.magnitude.orDash(),
                            style = MaterialTheme.typography.displayMedium.copy(
                                fontSize = 68.sp,
                                lineHeight = 72.sp
                            ),
                            color = heroTextColor
                        )

                        Text(
                            text = "MAGNITUDO (SR)",
                            style = MaterialTheme.typography.labelLarge.copy(
                                fontSize = 11.sp,
                                letterSpacing = 1.sp
                            ),
                            color = heroTextColor.copy(alpha = 0.85f)
                        )

                        Spacer(modifier = Modifier.height(14.dp))

                        Text(
                            text = gempa.wilayah.orDash(),
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontSize = 18.sp,
                                lineHeight = 24.sp
                            ),
                            color = heroTextColor,
                            textAlign = TextAlign.Center
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(28.dp))
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(20.dp)
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text(
                            text = "PARAMETER KEGEMPAAN",
                            style = MaterialTheme.typography.labelLarge.copy(
                                fontSize = 12.sp,
                                letterSpacing = 0.5.sp
                            ),
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )

                        DetailInfoRow(label = "Tanggal", value = gempa.tanggal.orDash())
                        DetailInfoRow(label = "Jam Gempa", value = gempa.jam.orDash())
                        DetailInfoRow(label = "Koordinat", value = gempa.coordinates.orDash())
                        DetailInfoRow(label = "Lintang & Bujur", value = "${gempa.lintang.orDash()} - ${gempa.bujur.orDash()}")
                        DetailInfoRow(label = "Kedalaman", value = gempa.kedalaman.orDash())

                        val isTsunami = gempa.potensi?.contains("tsunami", ignoreCase = true) == true
                        val badgeColor = if (isTsunami) {
                            if (isDark) CoralPinkDark else CoralPinkLight
                        } else {
                            if (isDark) DeepTealDark else DeepTealLight
                        }

                        Column(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = "Potensi",
                                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 12.sp),
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .border(
                                        width = 1.5.dp,
                                        color = badgeColor,
                                        shape = RoundedCornerShape(14.dp)
                                    )
                                    .background(
                                        color = badgeColor.copy(alpha = 0.12f),
                                        shape = RoundedCornerShape(14.dp)
                                    )
                                    .padding(horizontal = 14.dp, vertical = 10.dp)
                            ) {
                                Text(
                                    text = gempa.potensi.orDash(),
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        fontWeight = FontWeight.Bold
                                    ),
                                    color = badgeColor
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun DetailInfoRow(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 13.sp),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            ),
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.End
        )
    }
}
